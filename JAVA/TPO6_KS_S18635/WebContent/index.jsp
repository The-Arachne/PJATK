<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
  <head>
    <title>S18635</title>
    <style>
    	#myTable {
    		font-family: arial, sans-serif;
    		border-collapse: collapse;
    		width: 100%;
    	}
    	#myTable td, #myTable th {
    		border: 1px solid #999999;
    		text-align: left;
    		padding: 8px;
    	}
    	#myTable tr:nth-child(even) {
    		background-color: #dddddd;
    	}
    	#myInput {
		  background-image: url('https://cdn2.iconfinder.com/data/icons/font-awesome/1792/search-512.png');
		  background-position: 10px 10px;
		  background-repeat: no-repeat;
		  width: 50%;
		  font-size: 16px;
		  padding: 12px 20px 12px 40px;
		  border: 1px solid #ddd;
		  margin-bottom: 12px;
		}
    </style>
  </head>
  <body>
	<h1>KATALOG KSIĄŻEK</h1>
	<input type="text" id="myInput" onkeyup="myFunction()" placeholder="Search or Filter..." title="Type in a name">
	<hr>
	<%String[] lines=(String[]) request.getAttribute("list");
		for(String s:lines){%>
			<div>
				<%= s %>
			</div>
		<%}%>
		
	<script>
		function myFunction() {
		  var input, filter, table, tr,th, td, txtValue;
		  input = document.getElementById("myInput");
		  filter = input.value.toUpperCase();
		  table = document.getElementById("myTable");
		  tr = table.getElementsByTagName("tr");
		  th = table.getElementsByTagName("th");

		  for (var i = 0; i < tr.length; i++) {
			  for( var j=0;j<th.length;j++){
				  td = tr[i].getElementsByTagName("td")[j];
				    if (td) {
				      txtValue = td.textContent || td.innerText;
				      if (txtValue.toUpperCase().indexOf(filter) > -1) {
				        tr[i].style.display = "";
						break;
				      } else {
				        tr[i].style.display = "none";
				      }
				    }    
			  }
		  }
		}
	</script>	
		
  </body>
</html>