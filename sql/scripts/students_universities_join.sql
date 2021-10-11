select st.name, u.id, st.course, st.speciality, st.enroll_date from students as st join universities as u on st.university_id=u.id where u.id<3;
select st.name, u.id as University, st.course, st.speciality, st.enroll_date from students as st join universities as u on st.university_id=u.id;
select st.name, u.id as University, st.course, st.speciality, st.enroll_date from students as st join universities as u on st.university_id=u.id where u.id<3 and st.course>2;
select u.name as University_name, st.name as Sudent_name, st.course from universities as u join students as st on st.university_id=u.id where st.course < 3;
