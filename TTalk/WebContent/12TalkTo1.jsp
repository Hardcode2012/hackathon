
<%@ page import="com.att.api.util.DateUtil"%>
<%@ page import="com.att.api.speech.model.SpeechResponse"%>
<%@ page import="com.att.api.speech.handler.Config"%>
<html>
<head>
  
<meta name="author" content="Sajith Amma" />
  
<script src="jquery.min.js"> </script>
<script src="jRecorder.js"> </script>

<title>jRecorder Example</title>  

<style>

li {display:inline; margin-right:10px;}
</style>

 
</head>


<body> 
  
 
        
        
        

   
    <h3>jRecorder Example 1 - Insert flash recorder inside the body tag</h3>

    <p>In this example, you add a flash recorder with some call  back function. $.jRecorder function is called with all initial settings.
    If the second parameter for $.jRecorder is not there, plugin insert the flash recorder inside the body tag. You can specify where to insert
    the flash recorder with second parameter. See <a href="example2.html">example2.html</a> for this.
    </p>

    <p>Download the plugin: <a href="jRecorder.zip">jRecorder.zip</a> </p>

   <script>
   
   $.jRecorder(
     
     { 
        host : 'http://localhost:8080/TTalk/FileUpload?filename=hello.wav' ,  //replace with your server path please
        
        callback_started_recording:     function(){callback_started(); },
        callback_stopped_recording:     function(){callback_stopped(); },
        callback_activityLevel:          function(level){callback_activityLevel(level); },
        callback_activityTime:     function(time){callback_activityTime(time); },
        
        callback_finished_sending:     function(time){ callback_finished_sending() },
        
        
        swf_path : 'jRecorder.swf',
     
     }
     
   
        
   
   );
   
    
   
   </script>

</div>


<div style="background-color: #eeeeee;border:1px solid #cccccc">
  
  Time: <span id="time">00:00</span>
  
</div>


<div>
  Level: <span id="level"></span>
</div>  

<div id="levelbase" style="width:200px;height:20px;background-color:#ffff00">
  
  <div id="levelbar" style="height:19px; width:2px;background-color:red"></div>
  
</div>

<div>
  Status: <span id="status"></status>
</div> 


<div>
  
<input type="button" id="record" value="Record" style="color:red">  
<p>This Record button trigger the record event. See the javascript example in the bottom of the page. (View Source in your browser).
  
<pre>
$('#record').click(function(){
                    
    $.jRecorder.record(30); //record up to 30 sec and stops automatically
                   
   })
</pre>
  </p>  


<hr/>

<input type="button" id="stop" value="Stop">

<p>This Stop button trigger the stop record event.
  
<hr/>

  
<input type="button" id="send" value="Send Data">

<p>This SendData button trigger the sendData event to flash to send the wav data to Server (configured in the host parameter).  
  
  
<hr/>
  
<pre>
$('#stop').click(function(){
                    
    $.jRecorder.stop();
                   
   })
</pre>
  </p>  


<hr/>


</div>


 

<p>
  Time area is used to update the time. There is an event Listener which update the recording time dynamically.
  <pre>
    
    callback_activityTime:     function(time){callback_activityTime(time);  //see the initialisation
    
    //function callback
    function callback_activityTime(time)
     {
      
      
       $('#time').html(time);
       
     }
    
  </pre>  
</p>  
 


 

</body>
</html>


 <script type="text/javascript">

                  
      
      
      
                  $('#record').click(function(){
                    
                    
                      $.jRecorder.record(30);
                      
                      
                      
                    
                    
                  })
                  
                  
                  $('#stop').click(function(){
                    
                    
                    
                     $.jRecorder.stop();
                    
                    
                  })
                  
                  
                   $('#send').click(function(){
                    
                    
                    
                     $.jRecorder.sendData();
                    
                    
                  })
                  

                  function callback_finished()
                  {
      
                      $('#status').html('Recording is finished');
                    
                  }
                  
                  function callback_started()
                  {
      
                      $('#status').html('Recording is started');
                    
                  }
                  
                  
                  
                  
                  function callback_error(code)
                  {
                      $('#status').html('Error, code:' + code);
                  }
                  
                  
                  function callback_stopped()
                  {
                      $('#status').html('Stop request is accepted');
                  }

                  function callback_finished_recording()
                  {
                    
                      $('#status').html('Recording event is finished');
                    
                    
                  }
                  
                  function callback_finished_sending()
                  {
                    
                      $('#status').html('File has been sent to server mentioned as host parameter');
                      
                      
                  }
                  
                  function callback_activityLevel(level)
                  {
                    
                    $('#level').html(level);
                    
                    if(level == -1)
                    {
                      $('#levelbar').css("width",  "2px");
                    }
                    else
                    {
                      $('#levelbar').css("width", (level * 2)+ "px");
                    }
                    
                    
                  }
                  
                  function callback_activityTime(time)
                  {
                   
                   //$('.flrecorder').css("width", "1px"); 
                   //$('.flrecorder').css("height", "1px"); 
                    $('#time').html(time);
                    
                  }

                  
                  
		   
		   
		              

           
        </script>
		
