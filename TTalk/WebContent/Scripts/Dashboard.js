google.load('visualization', '1.0', { 'packages': ['corechart','gauge'] });
google.setOnLoadCallback(drawGraphs);

function drawGraphs() {
    drawChart();
    drawComplexChart();
    drawGauge1();
    drawGauge2();

}

function drawComplexChart() {
    var data = google.visualization.arrayToDataTable([
    ['Product', 'Forecast', 'Requested', 'Provided'],
    ['Dry Cereal', 200, 300, 250],
    ['Canned Vegetable', 150, 100, 100],
    ['Sugar', 300, 250, 250],
    ['Oil', 150, 175, 150],
    ['Evaporated Milk', 200, 250, 150],
    ['MacNCheese', 150, 100, 100]
  ]);

    // Create and draw the visualization.
    var ac = new google.visualization.ComboChart(document.getElementById('chart2'));
    ac.draw(data, {       
        width: 470,
        height: 266,
        vAxis: { title: "Weight in pounds" },
        hAxis: { title: "Product" },
        seriesType: "bars"
    });
}

function drawChart() {

    // Create the data table.
    var data = new google.visualization.DataTable();
    data.addColumn('string', 'Donation Type');
    data.addColumn('number', 'Equivalent Produce');
    data.addRows([
          ['Virtual Food Drive', 3],
          ['Canned Food Drive', 1],
          ['Direct Money Donation', 1]
        ]);

    // Set chart options
    var options = { width: 470,
        height: 266
    };

    // Instantiate and draw our chart, passing in some options.
    var chart = new google.visualization.PieChart(document.getElementById('chart1'));
    chart.draw(data, options);
}

function drawGauge1() {
    debugger;
    var data = google.visualization.arrayToDataTable([
          ['Label', 'Value'],
          ['Dry Cereal', 80],
          ['Dried Pasta', 35],
          ['MacNCheese', 5]
        ]);

    var options = {
        width: 475, height: 266,
        redFrom: 0, redTo: 10,
        yellowFrom: 10, yellowTo: 30,
        minorTicks: 5
    };

    var chart = new google.visualization.Gauge(document.getElementById('DryCerealGauge'));
    chart.draw(data, options);
}

function drawGauge2() {
    var data = google.visualization.arrayToDataTable([
          ['Label', 'Value'],
          ['Sugar', 25],
          ['Oil', 50],
          ['Jello', 68]
        ]);

    var options = {
        width: 475, height: 266,
        redFrom: 0, redTo: 10,
        yellowFrom: 10, yellowTo: 30,
        minorTicks: 5
    };

    var chart = new google.visualization.Gauge(document.getElementById('CannedVegetableGauge'));
    chart.draw(data, options);
}