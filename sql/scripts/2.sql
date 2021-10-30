select c.name, count(p.company_id) "count"
from company c
join person p
on c.id=p.company_id
group by c.name
having count(p.company_id) =
(
	select count(p.company_id) cnt
	from company c
	join person p
	on c.id=p.company_id
	group by c.name
	order by cnt desc
	limit 1
	)
order by c.name asc
;
