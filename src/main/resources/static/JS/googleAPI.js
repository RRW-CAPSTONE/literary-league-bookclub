"use strict";
(function(){

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
        console.log(response);

        // Loop through the search results and create Bootstrap cards
        $.each(response.items, function(index, item) {
            let title = item.volumeInfo.title;
            let authors = item.volumeInfo.authors ? item.volumeInfo.authors.join(', ') : 'Unknown';
            let thumbnail = item.volumeInfo.imageLinks ? item.volumeInfo.imageLinks.thumbnail : 'https://via.placeholder.com/128x196?text=No+Image';
            let description = item.volumeInfo.description ? item.volumeInfo.description : 'No description available.';

            const card =
                `<div class="col-lg-6 col-md-3 col-sm-4 mb-4">
                    <div class="card h-100">
                        <div class="row no-gutters">
                           <div class="col-md-4">
                                <img src="${thumbnail}" class="card-img-top" alt="${title}">
                           </div>
                           <div class="col-md-8">
                              <div class="card-body">
                                <h5 class="card-title">${title}</h5>
                                <hr>
                                <p class="card-text mb-auto"><strong>Author(s): </strong> ${authors}</p>
                                <p class="card-text overflow-auto" style="height: 9.5em;"><strong>Description: </strong> ${description}</p>
                              <div class="position-absolute bottom-0 end-0">
                                 <!--<a href="" class="btn btn-primary mt-auto">Learn More</a>-->
                                 <a href="#" class="btn mt-auto" data-bs-toggle="modal" data-bs-target="#book-modal" data-title="${title}" data-author="${authors}" data-description="${description}" data-thumbnail-url="${thumbnail}">Learn More</a>
                              </div>
                           </div>
                        </div>
                    </div>
                </div>
            </div>`;
            $('#search-results').append(card);
        });
    }).fail(function() {
            $('#search-results').html('<p>Sorry, an error occurred while processing your request.</p>');
        });

    $('#book-modal').on('show.bs.modal', function(event) {
        let button = $(event.relatedTarget);
        let title = button.data('title');
        let author = button.data('author');
        let thumbnailUrl = button.data('thumbnail-url');
        let description = button.data('description');

        $(this).find('.modal-title').text(title);
        $(this).find('.modal-author').text(author);
        $(this).find('#modal-description').text(description);
        $(this).find('#book-modal-thumbnail').attr('src', thumbnailUrl);
        $(this).find('#book-modal-thumbnail1').val(thumbnailUrl);
        $(this).find('#book-modal-title').val(title);
        $(this).find('#book-modal-author').val(author);
      });
    }
})();
