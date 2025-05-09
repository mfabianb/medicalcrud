# medicalcrud

EXAMPLES

Uses a H2 databases, and load some Catalog data from data.sql in resources folder

POST: http://localhost:8080/medical/appointment
{
    "appointmentDateTime": "2025-05-10T10:10:10",
    "idDoctor": 1,
    "idPatient": 1,
    "idConsultingRoom": 1
}
{
    "appointmentDateTime": "2025-05-11T10:10:10",
    "idDoctor": 1,
    "idPatient": 2,
    "idConsultingRoom": 2
}



GET: http://localhost:8080/medical/appointment
{
    "appointmentDateTime": null,
    "idDoctor": null,
    "idPatient": null,
    "idConsultingRoom": null
}