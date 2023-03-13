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

        // Loop through the search results and create Bootstrap cards
        $.each(response.items, function(index, item) {
            const title = item.volumeInfo.title;
            const authors = item.volumeInfo.authors ? item.volumeInfo.authors.join(', ') : 'Unknown';
            const thumbnail = item.volumeInfo.imageLinks ? item.volumeInfo.imageLinks.thumbnail : 'https://via.placeholder.com/128x196?text=No+Image';
            const description = item.volumeInfo.description ? item.volumeInfo.description : 'No description available.';
            // if (description.length > 140) {
            //     description = description.substring(0, 140) + '...';
            // }
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
                                 <a href="#" class="btn btn-primary mt-auto" data-bs-toggle="modal" data-bs-target="#book-modal" data-title="${title}" data-author="${authors}" data-description="${description}" data-thumbnail-url="${thumbnail}">Learn More</a>
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
        const button = $(event.relatedTarget);
        const title = button.data('title');
        const author = button.data('author');
        const thumbnailUrl = button.data('thumbnail-url');
        const description = button.data('description');

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
