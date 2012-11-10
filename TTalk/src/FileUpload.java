

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.h2.jdbcx.JdbcConnectionPool;

import com.att.api.speech.handler.Config;
import com.att.api.speech.model.SpeechResponse;
import com.att.api.speech.service.SpeechService;

/**
 * Servlet implementation class FileUpload
 */
public class FileUpload extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private JdbcConnectionPool cp   = null;
	@Override
       
    public void init() throws ServletException {
    	   try {
			Class.forName("org.h2.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	   
            this.cp = JdbcConnectionPool.create(
  		//the commented line can be used for creating file db, when we want to
  		//check DB for its correctness manually.
                    "jdbc:h2:file:~\\tempimportdb;DEFAULT_TABLE_TYPE=1;MULTI_THREADED=1;" +
  		//"jdbc:h2:mem:tempimportdb;DEFAULT_TABLE_TYPE=1;MULTI_THREADED=1;" +
                   "IGNORECASE=TRUE ",
                   "sa", "");
    	   final StringBuilder createTable = new StringBuilder("CREATE ");

           createTable.append(" TABLE ");
           createTable.append(" INVENTORY ");
           createTable.append(" ( quantity number,item varchar );");
           try {
			Connection conn = cp.getConnection();
			Statement stmt = conn.createStatement();
			stmt.execute(createTable.toString());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
           
    }
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FileUpload() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("do get");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("do Post");
		String fileName = request.getParameter("filename");
		System.out.println(fileName);
		InputStream is = request.getInputStream();
		//System.out.println("file size:"+is.read() );
		File f = new File(fileName);
		
		OutputStream os = new FileOutputStream(f);
		
		try{
			Config cfg  = new Config();

			cfg.clientIdAuth = "a289389ec2deed91c212a8b92787608c";
			cfg.clientSecretAuth = "1618afd4d2a08337";

			cfg.FQDN = "https://api.att.com";
			cfg.endPointURL = cfg.FQDN + "/rest/2/SpeechToText";

			cfg.speechContexts = new String[] { "BusinessSearch", "Websearch", "SMS", "Voicemail",
				                        "QuestionAndAnswer", "TV", "Generic" };
							

			// max size permitted for upload file
			cfg.maxUploadFileSize = 10 * 1024 * 1024; //10 mb

			cfg.defaultFile = "bostonCeltics.wav";

			String context = "Generic";
			String accessToken = (String) request.getSession().getAttribute("accessToken");
		 int read = 0;
         final byte[] bytes = new byte[1024];

         while ((read = is.read(bytes)) != -1) {
             os.write(bytes, 0, read);
         }
         os.flush();
         accessToken = "3745760baf8fc683922a78c5414d46fe";
         context = "BusinessSearch";
         System.out.println("AccessToken : " + accessToken);
         
         SpeechService speechService = new SpeechService(cfg);
         SpeechResponse speechResponse = speechService.sendRequest(f, accessToken,
					context);
         System.out.println(speechResponse);
         PrintWriter out = response.getWriter();
         String word ="apple12";
         if(speechResponse.getResult() != null){
        	 for (final String[] kvp : speechResponse.getResult()) {
					String key = kvp[0];
					System.out.println(key);
					if(key.equals("Words")){
						word = kvp[1];
						System.out.println(word);
					}
        	 }
         }
        
         Connection conn = cp.getConnection();
         String sql = "INSERT INTO INVENTORY(quantity,item) VALUES(?,?)" ;
         PreparedStatement pstmt = conn.prepareStatement(sql);
         String parameterIndex = word;
         pstmt.setInt(1, 1);
         pstmt.setString(2, word);
         pstmt.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
	}

}
