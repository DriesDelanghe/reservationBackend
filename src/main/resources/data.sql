insert into OPENING_DATE(ID, CLOSING_HOUR, OPENING_HOUR, OPENING_DATE, ACTIVE_DATE, REMOVED, event_name)
values (NEXTVAL('op_seq'), '23:30', '18:00', '2021-08-11', true, false, 'voetbal@Tjok'),
       (NEXTVAL('op_seq'), '23:30', '14:00', '2021-08-12', true, false, 'voetbal@Tjok'),
       (NEXTVAL('op_seq'), '23:00', '20:00', '2021-08-15', true, false, 'voetbal@Tjok'),
       (NEXTVAL('op_seq'), '23:00', '18:00', '2021-08-16', true, false, 'voetbal@Tjok'),
       (NEXTVAL('op_seq'), '23:30', '18:00', '2021-08-17', true, false, 'voetbal@Tjok'),
       (NEXTVAL('op_seq'), '23:30', '18:00', '2021-08-18', true, false, 'voetbal@Tjok'),
       (NEXTVAL('op_seq'), '23:30', '14:00', '2021-08-19', true, false, 'voetbal@Tjok'),
       (NEXTVAL('op_seq'), '23:00', '18:00', '2021-08-21', true, false, 'voetbal@Tjok'),
       (NEXTVAL('op_seq'), '23:00', '18:00', '2021-08-23', true, false, 'voetbal@Tjok'),
       (NEXTVAL('op_seq'), '23:30', '05:00', '2021-08-25', true, false, 'voetbal@Tjok'),
       (NEXTVAL('op_seq'), '23:30', '14:00', '2021-08-26', true, false, 'voetbal@Tjok'),
       (NEXTVAL('op_seq'), '23:00', '16:00', '2021-08-27', true, false, 'voetbal@Tjok'),
       (NEXTVAL('op_seq'), '23:00', '18:00', '2021-08-28', true, false, 'voetbal@Tjok'),
       (NEXTVAL('op_seq'), '23:00', '18:00', '2021-08-29', true, false, 'voetbal@Tjok'),
       (NEXTVAL('op_seq'), '23:00', '18:00', '2021-08-30', true, false, 'voetbal@Tjok'),
       (NEXTVAL('op_seq'), '23:30', '18:00', '2021-09-02', true, false, 'voetbal@Tjok'),
       (NEXTVAL('op_seq'), '23:30', '14:00', '2021-09-03', true, false, 'voetbal@Tjok'),
       (NEXTVAL('op_seq'), '23:30', '18:00', '2021-09-04', true, false, 'voetbal@Tjok'),
       (NEXTVAL('op_seq'), '23:00', '18:00', '2021-09-06', true, false, 'voetbal@Tjok'),
       (NEXTVAL('op_seq'), '23:00', '18:00', '2021-09-07', true, false, 'voetbal@Tjok'),
       (NEXTVAL('op_seq'), '23:00', '18:00', '2021-09-08', true, false, 'voetbal@Tjok'),
       (NEXTVAL('op_seq'), '23:30', '18:00', '2021-09-09', true, false, 'voetbal@Tjok'),
       (NEXTVAL('op_seq'), '23:30', '14:00', '2021-09-10', true, false, 'voetbal@Tjok'),
       (NEXTVAL('op_seq'), '23:00', '18:00', '2021-09-11', true, false, 'voetbal@Tjok'),
       (NEXTVAL('op_seq'), '23:00', '18:00', '2021-09-23', true, false, 'voetbal@Tjok'),
       (NEXTVAL('op_seq'), '23:00', '18:00', '2021-09-24', true, false, 'voetbal@Tjok'),
       (NEXTVAL('op_seq'), '23:00', '18:00', '2021-09-25', true, false, 'voetbal@Tjok'),
       (NEXTVAL('op_seq'), '23:00', '18:00', '2021-09-26', true, false, 'voetbal@Tjok'),
       (NEXTVAL('op_seq'), '23:00', '18:00', '2022-03-26', true, false, 'voetbal@Tjok');

insert into MONTH(id, month_name)
values (NEXTVAL('mo_seq'), 'januari'),
       (NEXTVAL('mo_seq'), 'februari'),
       (NEXTVAL('mo_seq'), 'maart'),
       (NEXTVAL('mo_seq'), 'april'),
       (NEXTVAL('mo_seq'), 'mei'),
       (NEXTVAL('mo_seq'), 'juni'),
       (NEXTVAL('mo_seq'), 'juli'),
       (NEXTVAL('mo_seq'), 'augustus'),
       (NEXTVAL('mo_seq'), 'september'),
       (NEXTVAL('mo_seq'), 'october'),
       (NEXTVAL('mo_seq'), 'november'),
       (NEXTVAL('mo_seq'), 'december')