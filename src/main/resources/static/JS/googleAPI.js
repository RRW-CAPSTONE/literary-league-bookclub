"use strict";
(function(){
    $(document).ready(function (){
        let cardContainer = $('#card-container');

        $('#searchButton').submit(function (e){
            e.preventDefault()
            let book = $('#bookSearch').val();

            $.ajax({
                method: 'GET',
                url: `https://www.googleapis.com/books/v1/volumes?q=${book}&key=${GOOGLE_API_KEY}`,
            }).done(function (data){
                console.log(data);
                let bookInfo = data.items[0].volumeInfo;
                let imgLink = bookInfo.imageLinks.thumbnail;
                let bookTitle = bookInfo.title;
                let authors = bookInfo.authors[0];
                let html = '';

                console.log(bookInfo);
                console.log(imgLink);

                const imgSrc = `${imgLink}`;

                console.log(imgSrc);

                const img = document.querySelector("img");

                console.log(img);

                img.setAttribute('src', imgSrc);

                html +=
                    '<p>' + bookTitle + '</p>' +
                    '<p>' + authors + '</p>'

                cardContainer.html(html);

            })
        })
    })
})();
