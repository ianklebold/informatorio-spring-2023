SELECT *
FROM book b JOIN author a on a.uuid = b.author_uuid
WHERE a.date_of_birth >= '1980-01-01' and a.date_of_birth < '1990-01-01';


SELECT *
FROM (
         SELECT b.uuid as idbook
         FROM book b JOIN author a on a.uuid = b.author_uuid
         WHERE a.date_of_birth >= '1980-01-01' and a.date_of_birth < '1990-01-01'
     ) as result1 JOIN book_reviews br ON br.book_uuid = result1.idbook JOIN review r ON r.uuid = br.reviews_uuid JOIN book b2 on b2.uuid = br.book_uuid
WHERE r.calification IN ('THREE_STARS','FOUR_STARS','FIVE_STARS');