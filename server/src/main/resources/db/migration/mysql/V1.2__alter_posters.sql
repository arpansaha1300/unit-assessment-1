ALTER TABLE poster
    CHANGE url vertical varchar(255) NOT NULL,
    ADD horizontal varchar(255) NOT NULL;

UPDATE poster SET horizontal = 'https://www.pixelstalk.net/wp-content/uploads/2016/08/Best-Nature-Full-HD-Images-For-Desktop.jpg' WHERE id = 1;
UPDATE poster SET horizontal = 'https://images.pexels.com/photos/462118/pexels-photo-462118.jpeg?cs=srgb&dl=bloom-blooming-blossom-462118.jpg&fm=jpg' WHERE id = 2;
UPDATE poster SET horizontal = 'https://www.pixelstalk.net/wp-content/uploads/2016/07/Free-Amazing-Background-Images-Nature.jpg' WHERE id = 3;
