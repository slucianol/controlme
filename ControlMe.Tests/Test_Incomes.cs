using ControlMe.WebApi.DataAccess.Entities;
using ControlMe.WebApi.DataAccess.Enums;
using ControlMe.WebApi.Services.Implementations;
using ControlMe.WebApi.Services.Interfaces;
using Moq;
using System;
using System.Collections.Generic;
using System.Text;
using Xunit;

namespace ControlMe.Tests {

    public class Test_Incomes {

        public Test_Incomes() {
        }

        [Fact]
        public void Test_AddNewIncome() {
            Income newIncome = new Income {
                Amount = 2300,
                ExecutionDate = DateTime.Now,
                Fixed = true,
                Type = IncomeType.Honorarios
            };
            Assert.Equal(IncomeType.Otro, newIncome.Type);
        }
    }
}
