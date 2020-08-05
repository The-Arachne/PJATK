using TakaWydmuszka.DTO_s;

namespace TakaWydmuszka.Services
{
    public interface IDbContext
    {
        public Project_OutPut getProject(int id);
        public void addTask(Task_Input input);
    }
}
