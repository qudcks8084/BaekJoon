import java.util.*;

class Task implements Comparable<Task>{
    int idx;
    int income_time;
    int working_time;
    
    public Task(int idx, int income_time, int working_time){
        this.idx = idx;
        this.income_time = income_time;
        this.working_time = working_time;
    }
    
    @Override
    public int compareTo(Task o){
        if(this.working_time == o.working_time){
            if(this.income_time == o.income_time)
                return Integer.compare(this.idx, o.idx);
            else
                return Integer.compare(this.income_time, o.income_time);
            
        }
        return Integer.compare(this.working_time, o.working_time);
    }
    
}

class Solution {
    public int solution(int[][] jobs) {
        
        int t = 0;
        int sum = 0;
        int N = jobs.length;
        
        // 입력 작업들을 요청 시간 순으로 정렬
        Arrays.sort(jobs, (o1, o2) -> o1[0] - o2[0]);

        PriorityQueue<Task> pq = new PriorityQueue<>();
        
        int jobIdx = 0;
        int count = 0;

        // for문 대신 모든 작업이 완료될 때까지(count < N) 반복하는 while문 사용
        while(count < N){

            // 만약 지금 들어오는 업무가 있는 경우
            // 작업 도중에 들어온 업무를 모두 삽입한다.
            while(jobIdx < N && jobs[jobIdx][0] <= t){
                int in_t = jobs[jobIdx][0];
                int wo_t = jobs[jobIdx][1];
                    
                pq.offer(new Task(jobIdx, in_t, wo_t));
                jobIdx++;
            }
            
            // 만약 큐가 비어있으면 바로 다음 작업의 시간대로 이동한다.
            
            if(pq.isEmpty()){
                t = jobs[jobIdx][0];
            }
            else{    
                // 작업이 끝나고 스택에 있는 작업을 빼는 경우
                // 꺼낸다.
                Task cur = pq.poll();
                
                // 그렇다면 해당 작업은 현재 t시간에 작동하는 것
                // t - income_time + working_time을 sum에 더한다.
                // t에 working_time를 더한다.
                
                // (대기 시간 + 작업 시간) 더하기
                sum += (t - cur.income_time) + cur.working_time;
                t += cur.working_time;
                
                count++;
            }
        }
        
        return sum / N;
    }
}