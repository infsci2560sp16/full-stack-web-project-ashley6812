			function addRow()
			{
				//add a row to the rows collection and get a reference to the newly added row
				var newRow = document.all("tblGrid").insertRow();
				
				//add 3 cells (<td>) to the new row and set the innerHTML to contain text boxes
				
				var oCell = newRow.insertCell();
				oCell.innerHTML = "<input type='text' name='t1'>";
				
				oCell = newRow.insertCell();
				oCell.innerHTML = "<input type='text' name='t2'>";
				
				oCell = newRow.insertCell();
				oCell.innerHTML = "<input type='text' name='t3'> &nbsp;&nbsp;<input type='button' value='Delete' onclick='removeRow(this);'/>";			
			}
			
			//deletes the specified row from the table
			function removeRow(src)
			{
				/* src refers to the input button that was clicked.	
				   to get a reference to the containing <tr> element,
				   get the parent of the parent (in this case case <tr>)
				*/			
				var oRow = src.parentElement.parentElement;		
				
				//once the row reference is obtained, delete it passing in its rowIndex			
				document.all("tblGrid").deleteRow(oRow.rowIndex);		
			}