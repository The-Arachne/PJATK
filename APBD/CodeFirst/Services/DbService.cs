using CodeFirst.Models;
using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.Linq;

namespace CodeFirst.Services
{
    public class DbService : Controller, IDbService
    {
        CodeFirstContext db;
        public DbService(CodeFirstContext xd)
        {
            db=xd;
        }

        public IActionResult AddDoctor(Doctor doctor)
        {
            var d = new Doctor
            {
                //index autonumer
                FirstName = doctor.FirstName,
                LastName = doctor.LastName,
                Email = doctor.Email,
                Prescription = new List<Prescription>()
            };
            db.Doctor.Add(d);
            db.SaveChanges();
            return Ok("dodano: "+d); 
        }

        public IActionResult DeleteDoctor(int id)
        {
            if (!db.Doctor.Where(e => e.IdDoctor == id).Any())
                return NotFound("Doktor o podanym indx nie istnieje: " + id);
            db.Doctor.Remove(db.Doctor.Where(e => e.IdDoctor == id).First());
            db.SaveChanges();
            return Ok("Usunieto: "+id);
        }

        public IActionResult GetDoctor(int id)
        {
            if (!db.Doctor.Where(e => e.IdDoctor == id).Any())
                return NotFound("Doktor o podanym indx nie istnieje: " + id);
            return Ok(db.Doctor.Where(e => e.IdDoctor == id).First());

        }

        public IActionResult ModifyDoctor(int id,DoctorUp doctor)
        {
            var d = new Doctor
            {
                IdDoctor = id,
                FirstName = doctor.FirstName,
                LastName = doctor.LastName,
                Email = doctor.Email,
                Prescription = doctor.Prescription
            };
            try{
              //  db.Attach(d);
               // db.Entry(d).Property("LastName").IsModified=true;
                db.Update(d);
                db.SaveChanges();
            }catch(Exception e){
                return NotFound("Dla: "+id+", wystapil blad:\n"+e);
            }
            return Ok("Updated: " + id);
        }
        public IActionResult RandomSeed()
        {
            try
            {
                var doc1 = new Doctor();
                doc1.FirstName = "Jan";
                doc1.LastName = "Kowalski";
                doc1.Email = "BestDocInTHEWorld@wp.pl";
                doc1.Prescription = new List<Prescription>();

                var pat1 = new Patient();
                pat1.FirstName = "Zawsze";
                pat1.LastName = "Chory";
                pat1.BithDate = DateTime.Now.AddYears(-20);
                pat1.Prescription = new List<Prescription>();

                var med1 = new Medicament();
                med1.Description = "Na ciągłe choroby";
                med1.Name = "Maść_Na_Choroby i tym podobne";
                med1.Type = "Maść";
                med1.PrescriptionXMedicament = new List<Prescription_Medicament>();

                db.Doctor.Add(doc1);
                db.Patient.Add(pat1);
                db.Medicament.Add(med1);
                db.SaveChanges();

                var pres1 = new Prescription();
                pres1.Doctor = doc1;
                pres1.Patient = pat1;
                pres1.IdPatient = db.Patient.Where(e => e.LastName == pat1.LastName && e.FirstName == pat1.FirstName && e.BithDate == pat1.BithDate).First().IdPatient;
                pres1.IdDoctor = db.Doctor.Where(e => e.LastName == doc1.LastName && e.FirstName == doc1.FirstName && e.Email == doc1.Email).First().IdDoctor;
                pres1.Date = DateTime.Now;
                pres1.DueDate = DateTime.Now.AddDays(20);
                pres1.PrescriptionXMedicament = new List<Prescription_Medicament>();

                db.Prescription.Add(pres1);
                db.SaveChanges();
                doc1.Prescription.Add(pres1);
                pat1.Prescription.Add(pres1);

                db.Update(doc1);
                db.Update(pat1);
                db.SaveChanges();

                var presXmedi1 = new Prescription_Medicament();
                presXmedi1.Details = "Przecena 10%, 3szt po 10 użyć ";
                presXmedi1.Medicament = med1;
                presXmedi1.IdMedicament = db.Medicament.Where(e => e.Name == med1.Name && e.Description == med1.Description && e.Type == med1.Type).First().IdMedicament;
                presXmedi1.Prescription = pres1;
                presXmedi1.IdPrescription = db.Prescription.Where(e => e.IdDoctor == pres1.IdDoctor && e.IdPatient == pres1.IdPatient).First().IdPrescription;

                db.Prescription_Medicaments.Add(presXmedi1);
                db.SaveChanges();
                pres1.PrescriptionXMedicament.Add(presXmedi1);
                med1.PrescriptionXMedicament.Add(presXmedi1);

                db.Update(pres1);
                db.Update(med1);
                db.SaveChanges();
            }
            catch (Exception e)
            {
                return NotFound("cos poszlo nie tak\n"+e);
            }
            return Ok("Chaos");
        }
    }
}
