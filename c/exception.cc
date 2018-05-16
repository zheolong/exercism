#include <iostream>
#include <exception>
#include <stdexcept>
using namespace std;

class CMyEXception :public std::logic_error
{
private:
    const std::string m_msg;

    const int m_errno;
public:
    explicit CMyEXception(const std::string &__arg,int ierrno = 0):logic_error(__arg),m_msg(__arg),m_errno(ierrno)
    {
        //
    } 

    virtual ~CMyEXception() throw()
    {
        std::cout<<"~CMyEXception()"<<endl;
    }
    
    virtual const char* what() const throw()
    {       
        //如有必要,覆盖基类的定义                           
         return m_msg.c_str();

   } 

    int geterrno() 
    {
        return m_errno;
    }               
};
                                                             
int main()                                                   
{       
        try
        {
             throw CMyEXception(std::string("dddd"),10); 
             std::cout<<"after throw,not execute"<<endl;
        }
        catch(const CMyEXception &e)
        {
           std::cout<<"CMyEXception:"<<e.what()<<endl;
        }        
        catch(const std::range_error &e)
        {
            std::cout<<"range_error:"<<e.what()<<endl;
        }        
        catch(const std::runtime_error &e)
        {
            std::cout<<"runtime_error:"<<e.what()<<endl;
        }      
        catch(const std::exception &e)
        {
            std::cout<<"exception:"<<e.what()<<endl;
        }
        catch(...)
        {
            std::cout<<"unexception ...:"<<endl;
        }                                
}
