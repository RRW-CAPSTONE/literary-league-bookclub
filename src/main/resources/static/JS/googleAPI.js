"use strict";
(function(){

    // Google Books API
    $(document).ready(function() {
        let bookInfo;
        let outputList = document.getElementById("list-output");
        let placeHolder = '<img src="https://via.placeholder.com/150" alt="">';
        let maxResults = 10; //variable to control the amount of results per page
        let startIndex = 0; //variable to control the position in the collection of volumes
        let totalResults = 0; //variable that holds the total number of results fetched with the GET request
        let previousIndex = 0;

        $('#searchButton').submit(function (e){
            e.preventDefault()
            let book = $('#bookSearch').val();
            outputList.innerHTML = ""; //empty html output

            //handling empty search input field
            if(book === "") {
                //$("#searchResults").append
                alert("No search text found, please try again");
            }
            else {
                requestBooks(book,startIndex,maxResults);
                // // Using AJAX to call Google book API
                // $.ajax({
                //     method: 'GET',
                //     url: `https://www.googleapis.com/books/v1/volumes?q=${book}&key=${Google_API_KEY}` + "&startIndex=" + startIndex + "&maxResults=" + maxResults ,
                // }).done(function (data, startIndex, ) {
                //     // Displaying the data in the console
                //     console.log(data);
                //     // If statement, that will alert the user if book doesn't exist. Else call the displayResults function to create the book cards
                //     if (data.totalItems === 0) {
                //         alert("no result!.. try again")
                //     }
                //     else {
                //         $("#title").animate({'margin-top': '5px'}, 1000); //search box animation
                //         $(".book-list").css("visibility", "visible");
                //         // displayResults(data);
                //         createPagination(data);
                //     }
                // });
             }
            event.preventDefault();
            // $("#searchButton").val(""); //clear search box
        });

        function requestBooks(book,startIndex,maxResults){
            // Using AJAX to call Google book API
            $.ajax({
                method: 'GET',
                url: `https://www.googleapis.com/books/v1/volumes?q=${book}&key=${Google_API_KEY}` + "&startIndex=" + startIndex + "&maxResults=" + maxResults ,
            }).done(function (data) {
                // Displaying the data in the console
                console.log(data);
                // If statement, that will alert the user if book doesn't exist. Else call the displayResults function to create the book cards
                if (data.totalItems === 0) {
                    alert("no result!.. try again")
                }
                else {
                    $("#title").animate({'margin-top': '5px'}, 1000); //search box animation
                    $(".book-list").css("visibility", "visible");
                    displayResults(data);
                    //createPagination(data);
                }
            });
        }

        // function createPagination(data, book){
        //     totalResults = data.totalItems;
        //     if (startIndex === 0 && totalResults > maxResults){
        //         previousIndex = startIndex;
        //         startIndex = startIndex + maxResults;
        //         $("#pagination").prepend("<a href='#' id='next'>Next</a>");
        //         //create the function to handle a click to the newly added "NEXT" link
        //         $("#next").click(function() {
        //             requestBooks(book,startIndex,maxResults);
        //             $('html, body').animate({ scrollTop: 0 }, 'slow'); //go to the top of the page, smoothly
        //             event.preventDefault();
        //         });
        //     }else{
        //         // checks if _startIndex is lower than total results and if the itens to display are the same as the maximum results per page
        //         if (startIndex !== 0 && startIndex < totalResults && data.items.length === maxResults) {
        //             $("#pagination").prepend("<a href='#' id='previous'>Previous</a><a href='#' id='next'>Next</a>");
        //             //create the function to handle a click to the newly added "NEXT" link
        //             $("#next").click(function () {
        //                 startIndex = startIndex + maxResults;
        //                 requestBooks(book,startIndex,maxResults);
        //                 $('html, body').animate({scrollTop: 0}, 'slow'); //go to the top of the page, smoothly
        //                 event.preventDefault();
        //             });
        //             //create the function to handle a click to the newly added "PREVIOUS" link
        //             $("#previous").click(function () {
        //                 startIndex = startIndex - maxResults;
        //                 requestBooks(book,startIndex,maxResults);
        //                 $('html, body').animate({scrollTop: 0}, 'slow');
        //                 event.preventDefault();
        //             });
        //             //when all the other checks fail it means there are no further Books to get. Only the "Previous" link is shown and _startIndex is set back by _maxResults amount to get the previous Books
        //         } else {
        //             if (startIndex !== 0) {
        //                 //cleanPagination();
        //                 //create the function to handle a click to the newly added PREVIOUS link
        //                 $("#pagination").prepend("<a href='#' id='previous'>Previous</a>");
        //                 $("#previous").click(function () {
        //                     startIndex = startIndex - maxResults;
        //                     requestBooks(book,startIndex,maxResults);
        //                     $('html, body').animate({scrollTop: 0}, 'slow'); ////go to the top of the page, smoothly
        //                     event.preventDefault();
        //                 });
        //             }
        //         }
        //     }
        // }

        // This Function
        function displayResults(data) {

            for (let i = 0; i < data.items.length; i+=2) {
                bookInfo = data.items[i];
                let title1 = bookInfo.volumeInfo.title;
                let author1 = bookInfo.volumeInfo.authors;
                let description1 = bookInfo.volumeInfo.description;
                let bookLink1 = bookInfo.volumeInfo.previewLink;
                let bookIsbn = bookInfo.volumeInfo.industryIdentifiers[1].identifier
                let bookImg1 = (bookInfo.volumeInfo.imageLinks) ? bookInfo.volumeInfo.imageLinks.thumbnail : placeHolder ;

                let bookInfo2 = data.items[i+1];
                let title2 = bookInfo2.volumeInfo.title;
                let author2 = bookInfo2.volumeInfo.authors;
                let description2 = bookInfo2.volumeInfo.description;
                let bookLink2 = bookInfo2.volumeInfo.previewLink;
                let bookIsbn2 = bookInfo2.volumeInfo.industryIdentifiers[1].identifier
                let bookImg2 = (bookInfo2.volumeInfo.imageLinks) ? bookInfo2.volumeInfo.imageLinks.thumbnail : placeHolder ;

                // in production code, item.text should have the HTML entities escaped.
                outputList.innerHTML += '<div class="row mt-4">' +
                                            formatOutput(bookImg1, title1, author1, description1, bookLink1, bookIsbn) +
                                            formatOutput(bookImg2, title2, author2, description2, bookLink2, bookIsbn2) +
                                        '</div>';
                console.log(outputList);
            }
        }

        // This function will create the Book card format
        function formatOutput(bookImg, title, author, description, bookLink, bookIsbn) {
            let viewUrl = 'readBook.html?isbn='+bookIsbn; // link for Book Viewer
            let htmlCard = `<div class="col-lg-6">
           <div class="card" style="">
             <div class="row no-gutters">
               <div class="col-md-4">
                 <img src="${bookImg}" class="card-img" alt="...">
               </div>
               <div class="col-md-8">
                 <div class="card-body">
                   <h5 class="card-title">${title}</h5>
                   <p class="card-text">Author: ${author}</p>
                   <p class="card-text overflow-auto" style="height: 11em;">Description: ${description}</p>
                   <a target="_blank" href="${viewUrl}" class="btn btn-secondary">Read Book</a>
                 </div>
               </div>
             </div>
           </div>
         </div>`
            return htmlCard;
        }

        //handling error for empty search box
        // function displayError() {
        //     alert("search box can not be empty!")
        // }
    });
})();
