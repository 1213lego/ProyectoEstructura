package mundo.facade;

import mundo.DAO.DepartamentoDao;

public class DepartamentoFacade extends AbsFacade<DepartamentoDao>
{
		public DepartamentoFacade() 
		{
			super(DepartamentoDao.class);
		}
}
