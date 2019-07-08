using ControlMe.WebApi.DataAccess.Entities;
using Microsoft.EntityFrameworkCore;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace ControlMe.WebApi.DataAccess {
    public class ControlMeDatabaseContext : DbContext {

        public DbSet<Income> Incomes { get; set; }
        public DbSet<Expense> Expenses { get; set; }
        public DbSet<Source> Sources { get; set; }

        public ControlMeDatabaseContext(DbContextOptions<ControlMeDatabaseContext> dbContextOptions) : base(dbContextOptions) {

        }
    }
}
