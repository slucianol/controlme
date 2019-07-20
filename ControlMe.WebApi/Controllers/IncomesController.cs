using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using ControlMe.WebApi.DataAccess.Entities;
using ControlMe.WebApi.Models;
using ControlMe.WebApi.Services.Interfaces;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;

namespace ControlMe.WebApi.Controllers {
    [Route("api/[controller]")]
    [ApiController]
    public class IncomesController : ControllerBase {
        private readonly IIncomeService incomeService;

        public IncomesController(IIncomeService incomeService) {
            this.incomeService = incomeService;
        }

        [HttpGet]
        public List<IncomeModel> Get() {
            return this.incomeService.Get().Select(income => new IncomeModel {
                Amount = income.Amount,
                ExecutionDate = income.ExecutionDate,
                Fixed = income.Fixed,
                Type = income.Type
            }).ToList();
        }

        [HttpGet("{id}")]
        public IncomeModel Get(Guid id) {
            Income income = this.incomeService.GetById(id);
            return new IncomeModel {
                Amount = income.Amount,
                ExecutionDate = income.ExecutionDate,
                Fixed = income.Fixed,
                Type = income.Type
            };
        }

        [HttpPost]
        public Income Post([FromBody]IncomeModel income) {
            return this.incomeService.Add(new Income {
                Amount = income.Amount,
                ExecutionDate = income.ExecutionDate,
                Fixed = income.Fixed,
                Type = DataAccess.Enums.IncomeType.Salario
            });
        }

        [HttpDelete("{id}")]
        public void Delete(Guid id) {
            this.incomeService.Delete(new Income { Id = id });
        }
    }
}