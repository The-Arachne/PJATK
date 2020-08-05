using Microsoft.EntityFrameworkCore.Migrations;

namespace CodeFirst.Migrations
{
    public partial class xd : Migration
    {
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropPrimaryKey(
                name: "PrescriptionMedicament_Prescription_PK",
                table: "Prescription_Medicaments");

            migrationBuilder.DropIndex(
                name: "IX_Prescription_Medicaments_IdMedicament",
                table: "Prescription_Medicaments");

            migrationBuilder.AddPrimaryKey(
                name: "PrescriptionMedicament_PK",
                table: "Prescription_Medicaments",
                columns: new[] { "IdMedicament", "IdPrescription" });

            migrationBuilder.CreateIndex(
                name: "IX_Prescription_Medicaments_IdPrescription",
                table: "Prescription_Medicaments",
                column: "IdPrescription");
        }

        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropPrimaryKey(
                name: "PrescriptionMedicament_PK",
                table: "Prescription_Medicaments");

            migrationBuilder.DropIndex(
                name: "IX_Prescription_Medicaments_IdPrescription",
                table: "Prescription_Medicaments");

            migrationBuilder.AddPrimaryKey(
                name: "PrescriptionMedicament_Prescription_PK",
                table: "Prescription_Medicaments",
                column: "IdPrescription");

            migrationBuilder.CreateIndex(
                name: "IX_Prescription_Medicaments_IdMedicament",
                table: "Prescription_Medicaments",
                column: "IdMedicament");
        }
    }
}
