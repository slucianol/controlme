using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using ControlMe.WebApi.DataAccess.Entities;
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
        public List<Income> Get() {
            return this.incomeService.Get().ToList();
        }

        [HttpGet("{id}")]
        public Income Get(Guid id) {
            return this.incomeService.GetById(id);
        }

        [HttpPost]
        public Income Post([FromBody]Income income) {
            return this.incomeService.Add(income); 
        }

        [HttpDelete]
        public void Delete(Income income) {
            this.incomeService.Delete(income);
        }
    }
}