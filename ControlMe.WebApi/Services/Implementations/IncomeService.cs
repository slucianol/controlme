using ControlMe.WebApi.DataAccess;
using ControlMe.WebApi.DataAccess.Entities;
using ControlMe.WebApi.Services.Interfaces;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace ControlMe.WebApi.Services.Implementations {
    public class IncomeService : IIncomeService {
        private readonly ControlMeDatabaseContext dbContext;

        public IncomeService(ControlMeDatabaseContext dbContext) {
            this.dbContext = dbContext;

        }

        public void Add(Income income) {
            this.dbContext.Incomes.Add(income);
            this.dbContext.SaveChanges();
        }

        public void Delete(Income income) {
            this.dbContext.Incomes.Remove(income);
            this.dbContext.SaveChanges();
        }

        public IQueryable<Income> Get() {
            return this.dbContext.Incomes.AsQueryable();
        }

        public Income GetById(Guid id) {
            return this.dbContext.Incomes.Where(i => i.Id == id).FirstOrDefault();
        }
    }
}
