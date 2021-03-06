import java.util.ArrayList;

public class TrajCompress {
	public static ArrayList<Point> pointList = null;
	public static void main(String[] args) {
		ArrayList<String> al = DPUtil
				.readFile("2007-10-14-GPS.log");
		pointList = new ArrayList<Point>();
		// point = new Point(0.0,0.0);
		for (int i = 0; i < al.size(); ++i) {
			String[] s = al.get(i).split(" ");
			double lon = new Double(s[3]).doubleValue();
			double lat = new Double(s[5]).doubleValue();
			double longitude = (int)(lon/100) + (((lon/100)-(int)(lon/100))*100)/60;
			double latitude =  (int)(lat/100) + (((lat/100)-(int)(lat/100))*100)/60;
			pointList.add(new Point(longitude, latitude,0));
			//System.out.println(longitude+" "+latitude);
		}
		int length = pointList.size();
		/*DPUtil.DouglasPeucker(0, pointList.size()-1, 30.0);
		int count = 0;
		for (int i = 0; i < pointList.size(); ++i) {
			if(pointList.get(i).getMark()==1){
				System.out.println(++count +" "+pointList.get(i).getLongitude()+" "+pointList.get(i)..getLatitude());
			}
		}*/
		ArrayList<Point> alist=DPUtil.DouglasPeucker(0, length-1, 30.0);
		ArrayList<String> als =new ArrayList<String>(alist.size());
		alist.add(0,pointList.get(0));
		alist.add(pointList.get(length-1));
		int count = 0;
		for (int i = 0; i < alist.size(); ++i) {
			als.add((i+1) +" "+alist.get(i).getLongitude()+" "+alist.get(i).getLatitude());
			count++;
				//System.out.println(i +" "+alist.get(i).getLongitude()+" "+alist.get(i).getLatitude());
		}
		System.out.println("Compress rate: "+String.format("%.2f", (double)count/length*100)+"%");
		DPUtil.writeFile(als);
	}

}
