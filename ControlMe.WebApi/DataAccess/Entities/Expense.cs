using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using ControlMe.WebApi.DataAccess.Enums;

namespace ControlMe.WebApi.DataAccess.Entities {
    public class Expense : Transaction {
        public ExpenseType Type { get; set; }
    }
}
