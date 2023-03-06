elencoFoto();

function elencoFoto(){
    axios.get('http://localhost:8080/api/foto').then((res) => {
        console.log(res.data)

        res.data.forEach(foto => {
            
            document.querySelector('#foto').innerHTML += 
            ` <div class="card my-3">        
                <a href="./show.html?id=${foto.id}">  <h4> ${foto.titolo} </h4> </a>
                <ul>
                    Tag:
                    <li>${foto.tag}</li>
                </ul>
                <p>${foto.descrizione}</p>
                <img src="${foto.url}" alt="${foto.titolo}">
            </div>`

        });

    }).catch((response) =>{
        alert('errore durante la richiesta');
    })
}

const formFoto = document.querySelector('#form-foto');

formFoto.addEventListener('submit', function(event) {
  event.preventDefault(); // Previeni il comportamento predefinito dell'invio del form

  const input = document.querySelector('#titolo').value;
  elencoFiltrato(input);
});

function elencoFiltrato(input){
    axios.get('http://localhost:8080/api/foto?titolo=' + input).then((res) => {
        console.log(res.data);

        // Svuota la lista di foto precedente
        document.querySelector('#foto').innerHTML = '';

        res.data.forEach(foto => {
            document.querySelector('#foto').innerHTML += 
            ` <div class="card my-3">        
                <a href="./show.html?id=${foto.id}">  <h4> ${foto.titolo} </h4> </a>
                <ul>
                    Tag:
                    <li>${foto.tag}</li>
                </ul>
                <p>${foto.descrizione}</p>
                <img src="${foto.url}" alt="${foto.titolo}">
            </div>`
        });

    }).catch((error) =>{
        console.error(error);
        alert('errore durante la richiesta');
    });
}



