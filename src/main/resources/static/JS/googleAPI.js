"use strict";
(function(){
    // Google Books API
    $(document).ready(function (){
        // Creating variable to query html ID
        let cardBody = $('#card');

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

                    html +=
                        '<div class="card" style="width: 14rem; margin-left: 2em;">' +
                            '<img src="'+ imgSrc + '" alt="" id="image">' +
                            '<div class="container">' +
                                '<h5>' + "Title: " + bookTitle + '</h5>' +
                                '<p>' + "Author: " + authors + '</p>' +
                                '<p>' + "Sorry book Description is not availible" + '</p>' +
                            '</div>' +
                        '</div>'
                    cardBody.html(html);

                } else if (authors == null){
                    const imgSrc = `${imgLink}`;

                    html +=
                        '<div class="card" style="width: 14rem; margin-left: 2em;">' +
                            '<img src="'+ imgSrc + '" alt="" id="image">' +
                            '<div class="container">' +
                                '<h5>' + "Title: " + bookTitle + '</h5>' +
                                '<p>' + "Sorry book Author is not availible" + '</p>' +
                                '<p>' + description + '</p>' +
                            '</div>' +
                        '</div>'
                    cardBody.html(html);

                } else if (bookTitle == null){
                    const imgSrc = `${imgLink}`;

                    html +=
                        '<div class="card" style="width: 14rem; margin-left: 2em;">' +
                            '<img src="'+ imgSrc + '" alt="" id="image">' +
                                '<div class="container">' +
                                '<h5>' + "Sorry book Title is not availible" + '</h5>' +
                                '<p>' + "Author: " + authors + '</p>' +
                                '<p>' + description + '</p>' +
                            '</div>' +
                        '</div>'
                    cardBody.html(html);

                }else {
                    const imgSrc = `${imgLink}`;

                    html +=
                        '<div class="card" style="width: 14rem; margin-left: 2em;">' +
                            '<img src="'+ imgSrc + '" alt="" id="image">' +
                            '<div class="container">' +
                                '<h5>' + "Title: " + bookTitle + '</h5>' +
                                '<hr>' +
                                '<p>' + "Author: " + authors + '</p>' +
                                '<hr>' +
                                '<p>' + description + '</p>' +
                            '</div>' +
                        '</div>'
                    cardBody.html(html);

                }

            })
        })
    })
})();
