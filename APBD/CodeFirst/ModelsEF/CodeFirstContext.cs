using Microsoft.EntityFrameworkCore;
using System;

namespace CodeFirst.Models
{
    public class CodeFirstContext:DbContext
    {
        public CodeFirstContext() { }
        public CodeFirstContext(DbContextOptions<CodeFirstContext> options) : base(options) { }

        public virtual DbSet<Patient> Patient { get; set; }
        public virtual DbSet<Prescription> Prescription { get; set; }
        public virtual DbSet<Doctor> Doctor { get; set; }
        public virtual DbSet<Medicament> Medicament { get; set; }
        public virtual DbSet<Prescription_Medicament> Prescription_Medicaments { get; set; }

        protected override void OnModelCreating(ModelBuilder modelBuilder)
        {
            modelBuilder.Entity<Patient>(e=>
            {
                e.HasKey(d => d.IdPatient).HasName("Patient_PK");

                e.Property(d => d.FirstName).HasMaxLength(100).IsRequired();
                e.Property(d=>d.LastName).HasMaxLength(100).IsRequired();
                e.Property(d => d.BithDate).IsRequired();
            });
            modelBuilder.Entity<Doctor>(e =>
            {
                e.HasKey(d => d.IdDoctor).HasName("Doctor_PK");

                e.Property(d => d.FirstName).HasMaxLength(100).IsRequired();
                e.Property(d => d.LastName).HasMaxLength(100).IsRequired();
                e.Property(d => d.Email).HasMaxLength(100).IsRequired();
            });
            modelBuilder.Entity<Medicament>(e =>
            {
                e.HasKey(d => d.IdMedicament).HasName("Medicament_PK");

                e.Property(d => d.Name).HasMaxLength(100).IsRequired();
                e.Property(d => d.Description).HasMaxLength(100).IsRequired();
                e.Property(d => d.Type).HasMaxLength(100).IsRequired();
            });

            modelBuilder.Entity<Prescription>(e=> 
            {
                e.HasKey(d => d.IdPrescription).HasName("Prescription_PK");

                e.Property(d => d.Date).IsRequired();
                e.Property(d => d.DueDate).IsRequired();

                e.HasOne(d => d.Patient)
                    .WithMany(d => d.Prescription)
                    .HasForeignKey(d=>d.IdPatient)
                    .OnDelete(DeleteBehavior.ClientSetNull)
                    .HasConstraintName("Prescription_Patient");

                e.HasOne(d=>d.Doctor)
                    .WithMany(d=>d.Prescription)//z tab doctor siegamy
                    .HasForeignKey(d=>d.IdDoctor)
                    .OnDelete(DeleteBehavior.ClientSetNull)
                    .HasConstraintName("Prescription_Doctor");
            });
            
            modelBuilder.Entity<Prescription_Medicament>(e =>
            {
                e.ToTable("Prescription_Medicament");
                e.HasKey(d =>new { d.IdMedicament , d.IdPrescription}).HasName("PrescriptionMedicament_PK");
                
                e.Property(d => d.Dose);
                e.Property(d => d.Details).HasMaxLength(100).IsRequired();

                e.HasOne(d=>d.Prescription)
                    .WithMany(d=>d.PrescriptionXMedicament)
                    .HasForeignKey(d=>d.IdPrescription)
                    .OnDelete(DeleteBehavior.ClientSetNull)
                    .HasConstraintName("PrescriptionMedicament_Prescription");

                e.HasOne(d => d.Medicament)
                    .WithMany(d => d.PrescriptionXMedicament)
                    .HasForeignKey(d => d.IdMedicament)
                    .OnDelete(DeleteBehavior.ClientSetNull)
                    .HasConstraintName("PrescriptionMedicament_Medicament");
            });
        }

    }
}
