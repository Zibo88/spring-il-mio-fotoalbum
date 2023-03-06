const URLParams=new URLSearchParams(window.location.search);
const fotoId=URLParams.get('id');

axios.get('http://localhost:8080/api/foto/' + fotoId).then((res) => {
    console.log('richiesta ok', res);

    document.querySelector('#titolo').innerHTML= `Titolo: ${res.data.titolo}`;
    document.querySelector('#tag').innerHTML= `Titolo: ${res.data.tag}`;
    document.querySelector('#descrizione').innerHTML= `Descrizione: ${res.data.descrizione}` ;
    document.querySelector('#url').setAttribute('src', res.data.url);

    res.data.categories.forEach(category => {
        document.querySelector('#categorie').innerHTML += 
        ` <li>${category.nome}</li>`
        
    });
    
}).catch((res) => {
    alert('Errore durante la richiesta!');
})
    
   
