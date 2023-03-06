"use strict";
(function(){
    $(document).ready(function (){

        $('#searchButton').submit(function (e){
            e.preventDefault()
            let book = $('#bookSearch').val();

            $.ajax({
                method: 'GET',
                url: `https://www.googleapis.com/books/v1/volumes?q=${book}&key=${Google_API_KEY}`,
            }).done(function (data){
                console.log(data);
            })
        })
    })
})();
