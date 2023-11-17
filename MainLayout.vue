<template>
  <q-layout view="lHh Lpr lFf">
        <h3>Type the query to send </h3>
        <q-input v-model="userInput" label="Enter your SQL query"></q-input>
        <br>
        <q-btn @click="saveAsDraft" label="save Table as Draft" />
        <q-btn @click="commit" label="Create the Table in PostGreSQL" />
        <q-btn @click="listDraft" label="list The Tables in Draft" />
        
  </q-layout>
</template>

<script>
  import axios from 'axios';

  export default {
    data() {
    return {
      userInput: ''
    };
  },
  methods: {
    // Your method to send data to Spring
    saveAsDraft() {
      // Your variable
      // const myVariable = "Select * from table";
      // const encodedData = encodeURIComponent(myVariable);

      if (!this.userInput.trim()) {
        return;
      }

      // Encode the user input before sending
      const encodedData = encodeURIComponent(this.userInput);
      // Make the HTTP POST request
      axios.post('http://localhost:8080/table/saveAsDraft',encodedData)
        .then(response => {
          console.log(response.data); // Handle the response from Spring if needed
          window.alert("Table saved in drafts");
        })
        .catch(error => {
          window.alert("..");
          console.error('Error sending data to Spring:', error);
        });
    },

    commit() {
      // Your variable
      // const myVariable = "Select * from table";
      // const encodedData = encodeURIComponent(myVariable);

      if (!this.userInput.trim()) {
        return;
      }

      // Encode the user input before sending
      const encodedData = encodeURIComponent(this.userInput);
      // Make the HTTP POST request
      axios.post('http://localhost:8080/table/execute',encodedData)
        .then(response => {
          console.log(response.data); // Handle the response from Spring if needed
          window.alert("Table created successfully");
        })
        .catch(error => {
          window.alert("Table already Exists");
          console.error('Error sending data to Spring:', error);
        });
    },
    
    listDraft() {
     
      axios.get('http://localhost:8080/table/get-draft-tables')
      .then(response => response.json())
  .then(data => {
    const tableContainer = document.getElementById('tableContainer'); // Assuming a container element

    // Assuming 'data' is an array of objects representing rows of the table
    const table = document.createElement('table');
    table.className = 'table'; // Assuming you have CSS classes for table styling

    // Assuming the first object in 'data' contains keys as column names
    const headers = Object.keys(data[0]);
    const headerRow = document.createElement('tr');
    headers.forEach(headerText => {
      const headerCell = document.createElement('th');
      headerCell.textContent = headerText;
      headerRow.appendChild(headerCell);
    });
    table.appendChild(headerRow);

    // Add rows and cells to the table
    data.forEach(rowData => {
      const row = document.createElement('tr');
      headers.forEach(header => {
        const cell = document.createElement('td');
        cell.textContent = rowData[header];
        row.appendChild(cell);
      });
      table.appendChild(row);
    });

    tableContainer.appendChild(table); // Append the table to the container
  });
    }
  }
  
};

</script>
