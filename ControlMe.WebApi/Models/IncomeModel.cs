using ControlMe.WebApi.DataAccess.Enums;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace ControlMe.WebApi.Models {
    public class IncomeModel {
        public decimal Amount { get; set; }
        public DateTime ExecutionDate { get; set; }
        public Source TransactionSource { get; set; }
        public bool Fixed { get; set; }
        public IncomeType Type { get; set; } 
    } 
} 
