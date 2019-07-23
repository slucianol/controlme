using ControlMe.WebApi.DataAccess.Enums;
using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Threading.Tasks;

namespace ControlMe.WebApi.Models {
    public class IncomeModel {
        [Required]
        public decimal Amount { get; set; }
        public DateTime ExecutionDate { get; set; }
        public Source TransactionSource { get; set; }
        [Required]
        public bool Fixed { get; set; }
        public IncomeType Type { get; set; } 
    }
}
