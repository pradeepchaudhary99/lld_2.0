// Handler Interface
abstract class SupportHandler {
    protected SupportHandler nextHandler;
    
    public void setNextHandler(SupportHandler nextHandler) {
        this.nextHandler = nextHandler;
    }
    
    public void handleRequest(SupportTicket ticket) {
        if (canHandle(ticket)) {
            process(ticket);
        } else if (nextHandler != null) {
            System.out.println("Cannot handle. Passing to next handler...\n");
            nextHandler.handleRequest(ticket);
        } else {
            System.out.println("No handler available for this request!");
        }
    }
    
    protected abstract boolean canHandle(SupportTicket ticket);
    protected abstract void process(SupportTicket ticket);
}

// Concrete Handlers
class Level1Support extends SupportHandler {
    @Override
    protected boolean canHandle(SupportTicket ticket) {
        return ticket.getPriority() <= 1;
    }
    
    @Override
    protected void process(SupportTicket ticket) {
        System.out.println("[Level 1 Support] Handling: " + ticket.getIssue());
        System.out.println("[Level 1 Support] Solution: Restart your device\n");
    }
}

class Level2Support extends SupportHandler {
    @Override
    protected boolean canHandle(SupportTicket ticket) {
        return ticket.getPriority() <= 2;
    }
    
    @Override
    protected void process(SupportTicket ticket) {
        System.out.println("[Level 2 Support] Handling: " + ticket.getIssue());
        System.out.println("[Level 2 Support] Solution: Check network settings\n");
    }
}

class Level3Support extends SupportHandler {
    @Override
    protected boolean canHandle(SupportTicket ticket) {
        return ticket.getPriority() <= 3;
    }
    
    @Override
    protected void process(SupportTicket ticket) {
        System.out.println("[Level 3 Support] Handling: " + ticket.getIssue());
        System.out.println("[Level 3 Support] Solution: Remote debugging session scheduled\n");
    }
}

// Request Object
class SupportTicket {
    private String issue;
    private int priority;
    
    public SupportTicket(String issue, int priority) {
        this.issue = issue;
        this.priority = priority;
    }
    
    public String getIssue() {
        return issue;
    }
    
    public int getPriority() {
        return priority;
    }
}

// Usage
public class SupportTicketDemo {
    public static void main(String[] args) {
        Level1Support level1 = new Level1Support();
        Level2Support level2 = new Level2Support();
        Level3Support level3 = new Level3Support();
        
        level1.setNextHandler(level2);
        level2.setNextHandler(level3);
        
        System.out.println("=== Support Ticket System ===\n");
        
        SupportTicket ticket1 = new SupportTicket("Login issue", 1);
        System.out.println("Ticket 1 - Priority: 1");
        level1.handleRequest(ticket1);
        
        SupportTicket ticket2 = new SupportTicket("Slow performance", 2);
        System.out.println("Ticket 2 - Priority: 2");
        level1.handleRequest(ticket2);
        
        SupportTicket ticket3 = new SupportTicket("Database corruption", 3);
        System.out.println("Ticket 3 - Priority: 3");
        level1.handleRequest(ticket3);
    }
}