-- Insert into movie
insert into movie (title, plot, trailer, rating) values
('Movie One', 'Lorem ipsum dolor sit amet consectetur adipisicing elit. Sequi ducimus rerum veniam recusandae. Perferendis pariatur optio repellendus veniam quis tempora illo corrupti expedita, maiores nulla debitis quaerat officia beatae. Obcaecati, laudantium molestias odit fugiat a eum. Ut, unde? Facere aut, maxime pariatur quibusdam iure repellendus expedita numquam facilis itaque asperiores! Praesentium vel nostrum, cum soluta, error earum quibusdam excepturi sit aut velit et sapiente assumenda voluptate, ab vitae minima vero esse sunt optio distinctio quisquam! Sed, fugit perspiciatis ab minima delectus quaerat sint voluptatibus.', 'https://www.youtube.com/embed/bEKt7LLEvb0?si=NjqBUuub_FOOSJoG', 4),
('Movie Two', 'Lorem ipsum dolor sit amet consectetur adipisicing elit. Sequi ducimus rerum veniam recusandae. Perferendis pariatur optio repellendus veniam quis tempora illo corrupti expedita, maiores nulla debitis quaerat officia beatae. Obcaecati, laudantium molestias odit fugiat a eum. Ut, unde? Facere aut, maxime pariatur quibusdam iure repellendus expedita numquam facilis itaque asperiores! Praesentium vel nostrum, cum soluta.', 'https://www.youtube.com/embed/bEKt7LLEvb0?si=NjqBUuub_FOOSJoG', 5),
('Movie Three', 'Lorem ipsum dolor sit amet consectetur adipisicing elit. Sequi ducimus rerum veniam recusandae. Perferendis pariatur optio repellendus veniam quis tempora illo corrupti expedita, maiores nulla debitis quaerat officia beatae.', 'https://www.youtube.com/embed/bEKt7LLEvb0?si=NjqBUuub_FOOSJoG', 3);

-- Insert into vendor
insert into vendor (name) values
('Amazon Prime'),
('Netflix'),
('Disney+');

-- Insert into movie_vendor
insert into movie_vendor (movie_id, vendor_id, price) values
(1, 1, 10),
(1, 2, 12),
(2, 1, 15),
(3, 3, 20);

-- Insert into poster
insert into poster (url, movie_id) values
('https://thumbs.dreamstime.com/z/vertical-close-up-view-eagle-toy-figurine-movie-secret-life-pets-vertical-close-up-view-257842823.jpg', 1),
('https://thumbs.dreamstime.com/z/vertical-close-up-view-eagle-toy-figurine-movie-secret-life-pets-vertical-close-up-view-257842823.jpg', 1),
('https://thumbs.dreamstime.com/z/vertical-close-up-view-eagle-toy-figurine-movie-secret-life-pets-vertical-close-up-view-257842823.jpg', 2);
