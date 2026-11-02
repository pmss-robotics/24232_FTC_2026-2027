package org.firstinspires.ftc.teamcode.util;

import com.seattlesolvers.solverslib.geometry.Vector2d;

public class States {
    public enum Alliance {
        Red(1,1), // For TeleOp input, flip the field centric inputs if on the Blue side for proper Pose control
        Blue(-1,-1),
        Unset(1,1);
        private final Vector2d direction;
        Alliance(int x, int y) {
            this.direction = new Vector2d(x, y);
        }

        public Vector2d inputDirection() {
            return direction;
        }
    }

    public enum RobotRunMode {
        Auto(true, true), // Starts Auto by setting new alliance and pose, resetting when starting
        TeleOp(true, false), // Starts TeleOp by reading old pose and alliance, not resetting
        Utility( false, true); // Starts Utility without using saved pose and resetting values
        private final boolean savePose, reset;
        RobotRunMode(boolean storePose, boolean reset) {
            this.savePose = storePose;
            this.reset = reset;
        }
        public boolean poseSaved() {
            return this.savePose;
        }

        public boolean reset() {
            return this.reset();
        }

    }
}
