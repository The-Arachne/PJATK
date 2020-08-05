using System;
using Microsoft.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore.Metadata;

namespace APBD_kolos2_powt_databaseFirst.EF_Models
{
    public partial class MasterContext : DbContext
    {
        public MasterContext()
        {
        }

        public MasterContext(DbContextOptions<MasterContext> options)
            : base(options)
        {
        }

        public virtual DbSet<Artist> Artist { get; set; }
        public virtual DbSet<ArtistEvent> ArtistEvent { get; set; }
        public virtual DbSet<Event> Event { get; set; }
        public virtual DbSet<EventOrganiser> EventOrganiser { get; set; }
        public virtual DbSet<Organiser> Organiser { get; set; }


        protected override void OnModelCreating(ModelBuilder modelBuilder)
        {
            modelBuilder.Entity<Artist>(entity =>
            {
                entity.HasKey(e => e.IdArtist)
                    .HasName("Artist_pk");

                entity.Property(e => e.IdArtist).ValueGeneratedNever();

                entity.Property(e => e.NickName)
                    .IsRequired()
                    .HasMaxLength(30);
            });

            modelBuilder.Entity<ArtistEvent>(entity =>
            {
                entity.HasKey(e => new { e.IdEvent, e.IdArtist })
                    .HasName("Artist_Event_pk");

                entity.ToTable("Artist_Event");

                entity.Property(e => e.PerformanceDate).HasColumnType("datetime");

                entity.HasOne(d => d.IdArtistNavigation)
                    .WithMany(p => p.ArtistEvent)
                    .HasForeignKey(d => d.IdArtist)
                    .OnDelete(DeleteBehavior.ClientSetNull)
                    .HasConstraintName("Artist_Event_Artist");

                entity.HasOne(d => d.IdEventNavigation)
                    .WithMany(p => p.ArtistEvent)
                    .HasForeignKey(d => d.IdEvent)
                    .OnDelete(DeleteBehavior.ClientSetNull)
                    .HasConstraintName("Artist_Event_Event");
            });

            modelBuilder.Entity<Event>(entity =>
            {
                entity.HasKey(e => e.IdEvent)
                    .HasName("Event_pk");

                entity.Property(e => e.IdEvent).ValueGeneratedNever();

                entity.Property(e => e.EndDate).HasColumnType("datetime");

                entity.Property(e => e.Name)
                    .IsRequired()
                    .HasMaxLength(100);

                entity.Property(e => e.StartDate).HasColumnType("datetime");
            });

            modelBuilder.Entity<EventOrganiser>(entity =>
            {
                entity.HasKey(e => new { e.IdEvent, e.IdOrganiser })
                    .HasName("Event_Organiser_pk");

                entity.ToTable("Event_Organiser");

                entity.HasOne(d => d.IdEventNavigation)
                    .WithMany(p => p.EventOrganiser)
                    .HasForeignKey(d => d.IdEvent)
                    .OnDelete(DeleteBehavior.ClientSetNull)
                    .HasConstraintName("Event_Organiser_Event");

                entity.HasOne(d => d.IdOrganiserNavigation)
                    .WithMany(p => p.EventOrganiser)
                    .HasForeignKey(d => d.IdOrganiser)
                    .OnDelete(DeleteBehavior.ClientSetNull)
                    .HasConstraintName("Event_Organiser_Organiser");
            });

            modelBuilder.Entity<Organiser>(entity =>
            {
                entity.HasKey(e => e.IdOrganiser)
                    .HasName("Organiser_pk");

                entity.Property(e => e.IdOrganiser).ValueGeneratedNever();

                entity.Property(e => e.Name)
                    .IsRequired()
                    .HasMaxLength(30);
            });

            OnModelCreatingPartial(modelBuilder);
        }

        partial void OnModelCreatingPartial(ModelBuilder modelBuilder);
    }
}
