select p.name "person", c.name "company"
from person p
join company c
on p.company_id=c.id
where c.id <> 5;