ALTER TABLE movies
ADD CONSTRAINT chk_rating CHECK (rating <= 5);
