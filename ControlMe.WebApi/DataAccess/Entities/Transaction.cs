using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace ControlMe.WebApi.DataAccess.Entities {
    public abstract class Transaction {
        public Guid Id { get; set; }
        public decimal Amount { get; set; }
        public DateTime ExecutionDate { get; set; }
        public Source TransactionSource { get; set; }
        public bool Fixed { get; set; }
    }
}
