const URLParams=new URLSearchParams(window.location.search);
const fotoId=URLParams.get('id');

axios.get('http://localhost:8080/api/foto/' + fotoId).then((res) => {
   

    document.querySelector('#titolo').innerHTML= `Titolo: ${res.data.titolo}`;
    document.querySelector('#tag').innerHTML= `Tag ${res.data.tag}`;
    document.querySelector('#descrizione').innerHTML= `${res.data.descrizione}` ;
    document.querySelector('#url').setAttribute('src', res.data.url);


    res.data.categories.forEach(category => {
        document.querySelector('#categorie').innerHTML += 
        ` <li>${category.nome}</li>`
        
    });

    if(res.data.message.length === 0){
        document.querySelector('#status').innerHTML = 'nessun messaggio presente'
    }else{
        res.data.message.forEach(singleMessage => {
            document.querySelector('#message').innerHTML += 
            `<div class="card single-message my-2 p-2">  
                <h5>${singleMessage.userName}</h5>
                <p>${singleMessage.userMessage}</p>
            </div>`
        }
        )};
    
}).catch((res) => {
    alert('Errore durante la richiesta!');
})


function submitMessage(event) {
    const URLParams=new URLSearchParams(window.location.search);
    const fotoId=URLParams.get('id');
    event.preventDefault(); // Evita il comportamento predefinito del form di ricaricare la pagina
  
    const userName = document.querySelector('#userName').value;
    const userMessage = document.querySelector('#userMessage').value;
  
    axios.post(`http://localhost:8080/api/foto/message/${fotoId}`, { userName, userMessage })
      .then((res) => {
          console.log(res)
        // Aggiorna la pagina per visualizzare il nuovo messaggio
        location.reload();
      })
      .catch((err) => {
        alert('Errore durante l\'invio del messaggio!');
      });
  }
  
  document.querySelector('#form-message').addEventListener('submit', submitMessage);
  
  
    
   
