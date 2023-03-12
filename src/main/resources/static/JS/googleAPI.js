"use strict";
(function(){

    // // Google Books API
    // $(document).ready(function() {
    //     let bookInfo;
    //     let outputList = document.getElementById("list-output");
    //     let placeHolder = '<img src="https://via.placeholder.com/150" alt="">';
    //     let maxResults = 10; //variable to control the amount of results per page
    //     let startIndex = 0; //variable to control the position in the collection of volumes
    //     let totalResults = 0; //variable that holds the total number of results fetched with the GET request
    //     let previousIndex = 0;
    //     // let numberOfBooks = data.items.length;
    //     // let booksPerPage = 10
    //     // let currentPage = 1
    //     // let numberOfPages = Math.ceil(numberOfBooks/booksPerPage)
    //
    //     $('#searchButton').submit(function (e){
    //         e.preventDefault()
    //         let book = $('#bookSearch').val();
    //         outputList.innerHTML = ""; //empty html output
    //
    //         //handling empty search input field
    //         if(book === "") {
    //             //$("#searchResults").append
    //             alert("No search text found, please try again");
    //         }
    //         else {
    //             // Calling requestBooks function to request Book API
    //             requestBooks(book,startIndex,maxResults);
    //         }
    //         event.preventDefault();
    //         // $("#searchButton").val(""); //clear search box
    //     });
    //
    //     // Using AJAX to call Google book API
    //     function requestBooks(book,startIndex,maxResults){
    //         // Using AJAX to call Google book API
    //         $.ajax({
    //             method: 'GET',
    //             url: `https://www.googleapis.com/books/v1/volumes?q=${book}&key=${Google_API_KEY}` + "&startIndex=" + startIndex + "&maxResults=" + maxResults ,
    //         }).done(function (data) {
    //             // Displaying the data in the console
    //             console.log(data);
    //             // If statement, that will alert the user if book doesn't exist. Else call the displayResults function to create the book cards
    //             if (data.totalItems === 0) {
    //                 alert("no result!.. try again")
    //             }
    //             else {
    //                 $("#title").animate({'margin-top': '5px'}, 1000); //search box animation
    //                 $(".book-list").css("visibility", "visible");
    //                 displayResults(data);
    //                 //createPagination(data);
    //             }
    //         });
    //     }
    //
    //     // This Function
    //     function displayResults(data) {
    //
    //         for (let i = 0; i < data.items.length; i+=2) {
    //             bookInfo = data.items[i];
    //             let title1 = bookInfo.volumeInfo.title;
    //             let author1 = bookInfo.volumeInfo.authors;
    //             let description1 = bookInfo.volumeInfo.description;
    //             //let bookLink1 = bookInfo.volumeInfo.previewLink;
    //             let bookIsbn = bookInfo.volumeInfo.industryIdentifiers[1].identifier
    //             let bookImg1 = (bookInfo.volumeInfo.imageLinks) ? bookInfo.volumeInfo.imageLinks.thumbnail : placeHolder ;
    //
    //             let bookInfo2 = data.items[i+1];
    //             let title2 = bookInfo2.volumeInfo.title;
    //             let author2 = bookInfo2.volumeInfo.authors;
    //             let description2 = bookInfo2.volumeInfo.description;
    //             //let bookLink2 = bookInfo2.volumeInfo.previewLink;
    //             let bookIsbn2 = bookInfo2.volumeInfo.industryIdentifiers[1].identifier
    //             let bookImg2 = (bookInfo2.volumeInfo.imageLinks) ? bookInfo2.volumeInfo.imageLinks.thumbnail : placeHolder ;
    //
    //             // in production code, item.text should have the HTML entities escaped.
    //             outputList.innerHTML += '<div class="row mt-4">' +
    //                                         formatOutput(bookImg1, title1, author1, description1, bookIsbn) +
    //                                         formatOutput(bookImg2, title2, author2, description2, bookIsbn2) +
    //                                     '</div>';
    //             console.log(outputList);
    //         }
    //     }
    //
    //     // This function will create the Book card format
    //
    //
    //     function formatOutput(bookImg, title, author, description, bookId) {
    //         // let viewUrl = 'readBook.html?isbn='+bookIsbn; // link for Book Viewer
    //         // console.log(bookImg);
    //         // console.log(title);
    //         // console.log(author);
    //         // console.log(description);
    //
    //         // let viewUrl = 'readBook.html?isbn='+bookIsbn; // link for Book Viewer
    //         let htmlCard = `<div class="col-lg-6">
    //        <div class="card" style="">
    //          <div class="row no-gutters">
    //            <div class="col-md-4">
    //              <img src="${bookImg}" class="card-img" alt="...">
    //            </div>
    //            <div class="col-md-8">
    //              <div class="card-body">
    //                <h5 class="card-title">${title}</h5>
    //                <p class="card-text">Author: ${author}</p>
    //                <p class="card-text overflow-auto" style="height: 11em;">Description: ${description}</p>
    //                 <!--<a target="_blank" href="" class="btn btn-secondary">Read Book</a>-->
    //
    //
    //                <!-- Button trigger modal -->
    //                 <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#modal${bookId}">
    //                   Add Book
    //                 </button>
    //
    //                 <!-- Modal -->
    //                 <div class="modal fade" id="modal${bookId}" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
    //                   <div class="modal-dialog">
    //                     <div class="modal-content">
    //                       <div class="modal-header">
    //                         <h5 class="modal-title" id="staticBackdropLabel">${title}</h5>
    //                         <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
    //                       </div>
    //                       <!--Modal Body-->
    //                       <div class="modal-body">
    //                         <div class="container-fluid">
    //                           <div class="row">
    //                             <div class="col-md-6">
    //                               <img src="${bookImg}" class="card-img" alt="...">
    //                             </div>
    //                             <div class="col-6">
    //                                <p class="card-text">Author: ${author}</p>
    //                                <hr>
    //                                <p class="card-text overflow-auto" style="height: 11em;">Description: ${description}</p>
    //                             </div>
    //                           </div>
    //                         </div>
    //                       </div>
    //                       <!--Modal Footer-->
    //                       <div class="modal-footer">
    //                         <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
    //                         <form action="/books/save" method="post">
    //                           <input type="hidden" name="title" id="title" value="${title}">
    //                           <input type="hidden" name="author" id="author" value="${author}">
    //                           <input type="hidden" name="bookId" value="${bookId}">
    // <!--                      <input type="hidden" name="description" id="Description" value="{description}">-->
    // <!--                      <input type="hidden" name="imgUrl" id="imgURL" value"{bookImg}">-->
    //                           <button type="submit" class="btn btn-primary" id="addABookButton">Add Book</button>
    //                         </form>
    //                       </div>
    //                     </div>
    //                   </div>
    //                 </div>
    //              </div>
    //            </div>
    //          </div>
    //        </div>
    //      </div>`
    //
    //         return htmlCard;
    //
    //     }
    //
    //     // document.getElementById('title').value = bookTitle;
    //     // document.getElementById('author').value = authors;
    //     // document.getElementById('description').value = description;
    //
    //     //handling error for empty search box
    //     // function displayError() {
    //     //     alert("search box can not be empty!")
    //     // }
    // });


    // Define the Google Books API endpoint and API key
    const endpoint = 'https://www.googleapis.com/books/v1/volumes';


// Handle form submission
    $('#search-form').on('submit', function(event) {
        event.preventDefault();
        const searchTerm = $('#search-input').val();
        searchBooks(searchTerm);
    });

// Search for books using the Google Books API
    function searchBooks(searchTerm) {
        const params = {
            q: searchTerm,
            key: Google_API_KEY
        };
        $.get(endpoint, params, function(response) {
            // Clear previous search results
            $('#search-results').empty();

            // Loop through the search results and create Bootstrap cards
            $.each(response.items, function(index, item) {
                const title = item.volumeInfo.title;
                const authors = item.volumeInfo.authors ? item.volumeInfo.authors.join(', ') : 'Unknown';
                const thumbnail = item.volumeInfo.imageLinks ? item.volumeInfo.imageLinks.thumbnail : 'https://via.placeholder.com/128x196?text=No+Image';
                let description = item.volumeInfo.description ? item.volumeInfo.description : 'No description available.';
                if (description.length > 140) {
                    description = description.substring(0, 140) + '...';
                }
                const card = `
	<div class="col-lg-2 col-md-3 col-sm-4 mb-4">
		<div class="card h-100">
			<img src="${thumbnail}" class="card-img-top" alt="${title}">
			<div class="card-body d-flex flex-column">
				<h5 class="card-title">${title}</h5>
				<p class="card-text mb-auto"><strong>Author(s):</strong> ${authors}</p>
				<p class="card-text">${description}</p>
<!--				<a href="${item.volumeInfo.infoLink}" class="btn btn-primary mt-auto">Learn More</a>-->
				<a href="#" class="btn btn-primary mt-auto" data-bs-toggle="modal" data-bs-target="#book-modal" data-title="${title}" data-author="${authors}" data-thumbnail-url="${thumbnail}">Learn More</a>
			</div>
		</div>
	</div>
`;
                $('#search-results').append(card);
            });
        })
            .fail(function() {
                $('#search-results').html('<p>Sorry, an error occurred while processing your request.</p>');
            });

        $('#book-modal').on('show.bs.modal', function(event) {
            const button = $(event.relatedTarget);
            const title = button.data('title');
            const author = button.data('author');
            const thumbnailUrl = button.data('thumbnail-url');

            $(this).find('.modal-title').text(title);
            $(this).find('#book-modal-thumbnail').attr('src', thumbnailUrl);
            // $(this).find('#book-modal-thumbnail').val(thumbnailUrl)
            $(this).find('#book-modal-title').val(title);
            $(this).find('#book-modal-author').val(author);
        });

    }



})();
