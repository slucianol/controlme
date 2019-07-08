using ControlMe.WebApi.DataAccess.Entities;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace ControlMe.WebApi.Services.Interfaces {
    public interface IIncomeService {
        IQueryable<Income> Get();
        Income GetById(Guid id);
        void Add(Income income);
        void Delete(Income income);
    }
}
