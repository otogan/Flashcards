/* Please, do not rename it */
class Problem {

    public static void main(String args[]) {
        String operator = args[0];
        // write your code here
        int[] nums = new int[args.length - 1];
        for (int i = 1; i < args.length; i++) {
            nums[i - 1] = Integer.parseInt(args[i]);
        }
        int result = 0;
        switch (operator) {
            case "MAX":
                result = Integer.MIN_VALUE;
                for (int i = 0; i < nums.length; i++) {
                    result = Math.max(result, nums[i]);
                }
                break;
            case "MIN":
                result = Integer.MAX_VALUE;
                for (int i = 0; i < nums.length; i++) {
                    result = Math.min(result, nums[i]);
                }
                break;
            case "SUM":
                for (int i = 0; i < nums.length; i++) {
                    result += nums[i];
                }
        }
        System.out.println(result);
    }
}