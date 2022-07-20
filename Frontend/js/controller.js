/**
 * Function to create a new contact
 */

function crear() {

    let infoName = document.getElementById("infoName").value;
    let infoCell = document.getElementById("infoCell").value;
    let infoEmail = document.getElementById("infoEmail").value;
    let infoDob = document.getElementById("infoDob").value;
    console.log('infoName :>> ', infoName);
    console.log('infoCell :>> ', infoCell);
    console.log('infoEmail :>> ', infoEmail);
    console.log('infoDob :>> ', infoDob);

    if (infoName == "" || infoCell == "" || infoEmail == "" || infoDob == "") {
        window.alert("missing information please complete all");
    } else {
        let data = {
            name: infoName,
            cellphone: infoCell,
            email: infoEmail,
            dob: infoDob,
            logicDelete : "0"
        }
        axios.post('http://localhost:9090/contact', data).then(resp => {
            window.alert("Data Save");
            traer();
            clear();

        }).catch(function (error) {
            window.alert(error);
        });
    }
}


/**
 * Function to clear form after create contact
 */

function clear() {
    document.getElementById("infoName").value = "";
    document.getElementById("infoCell").value = "";
    document.getElementById("infoEmail").value = "";
    document.getElementById("infoDob").value = "";

}

/**
 * Function to clear form after create contact
 */

function traer() {

    axios.get('http://localhost:9090/contacts').then(resp => {


        if (document.getElementsByTagName("tbody")[0]) {
            let row = document.getElementsByTagName("tbody")[0]
            row.parentNode.removeChild(row)
        }

        var tableRef = document.getElementById("tbl");
        var tBody = document.createElement("tbody");
        tBody.setAttribute("name", "tbody");

        resp.data.forEach(element => {

            console.log(element);
            if (element.logicDelete == 0) {

                  // Get the modal
                   var modal = document.getElementById("myModal");
                   // Get the button that opens the modal
                   var btn = document.getElementById("myBtn");
                   // Get the <span> element that closes the modal
                   var span = document.getElementsByClassName("close")[0];

                let tr = document.createElement("tr");
                let tdId = document.createElement("td");
                let tdName = document.createElement("td");
                let tdCellphone = document.createElement("td");
                let tdEmail = document.createElement("td");
                let tdDob = document.createElement("td");
                let tdEdit = document.createElement("td");
                let tdBtnEdit = document.createElement("button");
                tdBtnEdit.setAttribute("name", "btnEdit");
                let tdErase = document.createElement("td");
                let tdBtnErase = document.createElement("button");
                let tdBtnLogic = document.createElement("button");

                tdId.textContent = element.id;
                tdName.textContent = element.name;
                tdCellphone.textContent = element.cellphone;
                tdEmail.textContent = element.email;
                tdDob.textContent = element.dob;
                tdBtnEdit.textContent = "Edit";
                tdBtnEdit.addEventListener("click", function () {
                              
                    modal.style.display = "block";
                   let bntClose =  document.getElementById("btnClose");
                   bntClose.addEventListener("click",function(){
                    modal.style.display = "none";
                   })
                    window.onclick = function (event) {
                        if (event.target == modal) {
                            modal.style.display = "none";
                        }
                    }

                    document.getElementById("updateName").value = element.name
                    document.getElementById("updateCell").value = element.cellphone
                    document.getElementById("updateEmail").value = element.email
                    document.getElementById("updateDob").value = element.dob
                    document.getElementById("btnUpdate").addEventListener("click", function(){

                        
                        let data = {
                            name: document.getElementById("updateName").value,
                            cellphone: document.getElementById("updateCell").value,
                            email: document.getElementById("updateEmail").value,
                            dob: document.getElementById("updateDob").value,
                            logicDelete : "0"
                        }
                        
                        updateContact(element.id,data);

                    })

                  
                });
                tdBtnErase.textContent = "Delete";
                tdBtnErase.id = element.id;
                tdBtnErase.addEventListener("click", function () {
                    deleteContact(tdBtnErase.id)
                });
                tdBtnLogic.textContent = "Logic Delete";
                tdBtnLogic.addEventListener("click", function () {
                    deleteContactLogic(tdBtnErase.id)
                });


                tdErase.appendChild(tdBtnErase);
                tdErase.appendChild(tdBtnLogic);
                tdEdit.appendChild(tdBtnEdit);

                tr.appendChild(tdId);
                tr.appendChild(tdName);
                tr.appendChild(tdCellphone);
                tr.appendChild(tdEmail);
                tr.appendChild(tdDob);
                tr.appendChild(tdEdit);
                tr.appendChild(tdErase);


                tBody.appendChild(tr);
            }
        });

        tableRef.appendChild(tBody);

    });

}

/**
 * Function to update contact
 */

function updateContact(id,body) {
    console.log("body",body);

    axios.post('http://localhost:9090/contact/' + id, body).then(resp => {traer() });

}

/**
 * Function to delete contact
 */

function deleteContact(id) {
    axios.delete('http://localhost:9090/contact/' + id).then(resp => { traer() });

}

/**
 * Function to delete contact
 */

function deleteContactLogic(id) {
    console.log('id :>> ', id);
    axios.post('http://localhost:9090/contact/logicDelete/' + id, { logicDelete: "1" }).then(resp => { traer() });


}










