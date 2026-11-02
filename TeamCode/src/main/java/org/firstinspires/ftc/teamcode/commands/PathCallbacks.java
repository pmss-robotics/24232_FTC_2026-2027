package org.firstinspires.ftc.teamcode.commands;

import com.pedropathing.follower.Follower;
import com.seattlesolvers.solverslib.command.Command;
import com.seattlesolvers.solverslib.command.Commands;
import com.seattlesolvers.solverslib.command.ParallelCommandGroup;
import com.seattlesolvers.solverslib.command.WaitUntilCommand;
import com.seattlesolvers.solverslib.pedroCommand.FollowPathCommand;

public class PathCallbacks {
    public static Command parametricPathCallback(FollowPathCommand followPath, Follower follower, Command callback, double tValue) {
        return new ParallelCommandGroup(
                followPath,
                new WaitUntilCommand(follower::atParametricEnd)
                        .andThen(callback)

        );
    }

    public static Command temporalPathCallback(FollowPathCommand followPath, Command callback, long ms) {
        return new ParallelCommandGroup(
                followPath,
                Commands.waitMillis(ms).andThen(callback)
        );
    }

    public static Command completionPercentPathCallback(FollowPathCommand followPath, Follower follower, Command callback, double percent) {
        return new ParallelCommandGroup(
                followPath,
                new WaitUntilCommand(() -> followPath.isFinished() || follower.completion() > percent)
                        .andThen(callback)
        );
    }
}
