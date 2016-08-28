package outputconsole;

import pipeAndFilter.IAsyncFilter;

public interface IAsyncOutput extends IOutput, IAsyncFilter<Iterable<String>, String>{

}
