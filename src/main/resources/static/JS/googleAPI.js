"use strict";
(function(){
    // Google Books API
    $(document).ready(function (){
        // Creating variable to
        let cardContainer = $('#card-container');

        // Getting & submitting the users input value from the HTML form(title or authors) to return the users desired book
        $('#searchButton').submit(function (e){
            e.preventDefault()
            let book = $('#bookSearch').val();

            // Using AJAX to call Google book API
            $.ajax({
                method: 'GET',
                url: `https://www.googleapis.com/books/v1/volumes?q=${book}&key=${Google_API_KEY}`,
            }).done(function (data){
                // Consoling the data
                console.log(data);
                // Creating variables to select & pull certain data from the first array of books
                let bookInfo = data.items[0].volumeInfo;
                let imgLink = bookInfo.imageLinks.thumbnail;
                let bookTitle = bookInfo.title;
                let authors = bookInfo.authors[0];
                let description = data.items[0].volumeInfo.description
                let html = '';

                // Conditionals, In case Description/Title/Authors are undefined we'll print out a error message
                if (description == null){
                    const imgSrc = `${imgLink}`;

                    const img = document.querySelector("img");

                    img.setAttribute('src', imgSrc);

                    html +=
                        '<p>' + bookTitle + '</p>' +
                        '<p>' + authors + '</p>' +
                        // Error Message
                        '<p>' + "Sorry but description is not availible" + '</p>'
                    // Pushing data to card container in html
                    cardContainer.html(html);

                } else if (authors == null){
                    const imgSrc = `${imgLink}`;

                    const img = document.querySelector("img");

                    img.setAttribute('src', imgSrc);

                    html +=
                        '<p>' + bookTitle + '</p>' +
                        // Error Message
                        '<p>' + "Sorry but authors is not availible" + '</p>' +
                        '<p>' + description + '</p>'

                    cardContainer.html(html);

                } else if (bookTitle == null){
                    const imgSrc = `${imgLink}`;

                    const img = document.querySelector("img");

                    img.setAttribute('src', imgSrc);

                    html +=
                        // Error Message
                        '<p>' + "Sorry but the title is not availible" + '</p>' +
                        '<p>' + authors + '</p>' +
                        '<p>' + description + '</p>'

                    cardContainer.html(html);

                }else {
                    const imgSrc = `${imgLink}`;

                    const img = document.querySelector("img");

                    img.setAttribute('src', imgSrc);

                    html +=
                        '<p>' + bookTitle + '</p>' +
                        '<p>' + authors + '</p>' +
                        '<p>' + description + '</p>'

                    cardContainer.html(html);
                }

            })
        })
    })
})();
