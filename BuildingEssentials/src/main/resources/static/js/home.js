$(document).ready(function(){
   
  $.ajax({
    url: "http://localhost:8080/ordersGraph",
    method: "GET",
    success: function(data) {
      console.log(data);
      var order = [];
      var productId = [];

      for(var i in data) {
        order.push("Order " + data[i].orderId);
        productId.push(data[i].productId);
      }

      var chartdata = {
        labels: order,
        datasets : [
          {
            label: 'Order Product',
         
//            backgroundColor: 'rgba(200, 200, 200, 0.75)',
               backgroundColor:'rgb(106, 90, 205)',
            borderColor: 'rgba(200, 200, 200, 0.75)',
            hoverBackgroundColor: 'rgba(200, 200, 200, 1)',
            hoverBorderColor: 'rgba(200, 200, 200, 1)',
            data: productId
          }
        ]
        };

      var ctx = $("#mycanvas");
     

      var barGraph = new Chart(ctx, {
        type: 'bar',
        data: chartdata,
        options: {
            scales:{
                xAxes:[{
                        barThickness:60,
                        maxBarThickness:100
                }]
            }
        }
      });
    },
    error: function(data) {
      console.log(data);
    }
  });
    
   });
  

