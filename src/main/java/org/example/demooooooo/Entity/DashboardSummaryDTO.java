package org.example.demooooooo.Entity;

import jakarta.persistence.Entity;

@Entity
public class DashboardSummaryDTO
{
      private double youOwe;
        private double youAreOwed;

        public double getYouOwe() {
            return youOwe;
        }

        public void setYouOwe(double youOwe) {
            this.youOwe = youOwe;
        }

        public double getYouAreOwed() {
            return youAreOwed;
        }

        public void setYouAreOwed(double youAreOwed) {
            this.youAreOwed = youAreOwed;
        }
}


