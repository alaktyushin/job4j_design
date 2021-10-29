select c.name "company name", count(p.company_id) "employees count"
from company c
join person p
on c.id=p.company_id
group by c.name
order by "employees count" desc
limit 1;