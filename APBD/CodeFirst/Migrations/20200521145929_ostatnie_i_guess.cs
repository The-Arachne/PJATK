using Microsoft.EntityFrameworkCore.Migrations;

namespace CodeFirst.Migrations
{
    public partial class ostatnie_i_guess : Migration
    {
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.RenameTable(
                name: "Prescription_Medicaments",
                newName: "Prescription_Medicament");

            migrationBuilder.RenameIndex(
                name: "IX_Prescription_Medicaments_IdPrescription",
                table: "Prescription_Medicament",
                newName: "IX_Prescription_Medicament_IdPrescription");
        }

        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.RenameTable(
                name: "Prescription_Medicament",
                newName: "Prescription_Medicaments");

            migrationBuilder.RenameIndex(
                name: "IX_Prescription_Medicament_IdPrescription",
                table: "Prescription_Medicaments",
                newName: "IX_Prescription_Medicaments_IdPrescription");
        }
    }
}
