INSERT INTO movie (title, plot, trailer, rating) VALUES
('Movie Four', 'Lorem ipsum dolor sit amet consectetur adipisicing elit. Sequi ducimus rerum veniam recusandae. Perferendis pariatur optio repellendus veniam quis tempora illo corrupti expedita, maiores nulla debitis quaerat officia beatae.', 'https://www.youtube.com/embed/bEKt7LLEvb0?si=NjqBUuub_FOOSJoG', 5),
('Movie Five', 'Lorem ipsum dolor sit amet consectetur adipisicing elit. Sequi ducimus rerum veniam recusandae. Perferendis pariatur optio repellendus veniam quis tempora illo corrupti expedita, maiores nulla debitis quaerat officia beatae. Obcaecati, laudantium molestias odit fugiat a eum. Ut, unde? Facere aut, maxime pariatur quibusdam iure repellendus expedita numquam facilis itaque asperiores! Praesentium vel nostrum, cum soluta, error earum quibusdam excepturi sit aut velit et sapiente assumenda voluptate, ab vitae minima vero esse sunt optio distinctio quisquam! Sed, fugit perspiciatis ab minima delectus quaerat sint voluptatibus.', 'https://www.youtube.com/embed/bEKt7LLEvb0?si=NjqBUuub_FOOSJoG', 4),
('Movie Six', 'Error earum quibusdam excepturi sit aut velit et sapiente assumenda voluptate, ab vitae minima vero esse sunt optio distinctio quisquam! Sed, fugit perspiciatis ab minima delectus quaerat sint voluptatibus.', 'https://www.youtube.com/embed/bEKt7LLEvb0?si=NjqBUuub_FOOSJoG', 3);

INSERT INTO movie_vendor (movie_id, vendor_id, price) VALUES
(2, 2, 14), -- Movie Two on Netflix
(2, 3, 13), -- Movie Two on Disney+
(3, 1, 11), -- Movie Three on Amazon Prime
(3, 2, 10), -- Movie Three on Netflix
(4, 1, 16), -- Movie Four on Amazon Prime
(5, 2, 13), -- Movie Five on Netflix
(6, 3, 18); -- Movie Six on Disney+

INSERT INTO poster (vertical, horizontal, movie_id) VALUES
('https://th.bing.com/th/id/OIP.27qZJLSoIulU-7LMsHe1_QAAAA?rs=1&pid=ImgDetMain', 'https://th.bing.com/th/id/OIP.NlnBf5kY-XIslELFSSTZAQHaEK?rs=1&pid=ImgDetMain', 3),
('https://cdn.wallpapersafari.com/21/19/6WrNXf.jpg', 'https://wallpapercave.com/wp/wp4566625.jpg', 4),
('https://wallpapercave.com/wp/wp8111763.jpg', 'https://wallpaperaccess.com/full/1349247.jpg', 4),
('https://wallpaperaccess.com/full/2646421.jpg', 'https://wallpapercave.com/wp/wp4566625.jpg', 5),
('https://wallup.net/wp-content/uploads/2018/03/19/579863-vertical-portrait_display.jpg', 'https://wallpapercave.com/wp/wp4566625.jpg', 6),
('https://th.bing.com/th/id/OIP.N64nV2PGkVV7ctKCKRzYlwHaNK?rs=1&pid=ImgDetMain', 'https://th.bing.com/th/id/OIP.Je7fCvEAwarT5PzMXU3AZgHaEK?rs=1&pid=ImgDetMain', 6);
