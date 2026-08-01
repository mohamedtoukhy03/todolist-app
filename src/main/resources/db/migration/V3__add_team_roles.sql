-- Add team_role column to user_and_team table
ALTER TABLE user_and_team 
  ADD COLUMN team_role VARCHAR(50) NOT NULL DEFAULT 'TEAM_MEMBER';
